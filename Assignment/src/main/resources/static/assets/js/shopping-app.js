const app = angular.module('shopping-app', []);

app.controller('shopping-ctrl', function ($scope, $http) {

    //Khai báo index
    $scope.index = 0;
    //index tự tăng trong bảng sản phẩm
    $scope.incrementIndex = function () {
        $scope.index++;
    }

    //Button load more 
    $scope.loadMore = function (index) {
        alert(index);
        // $http.get(`/rest/products/load-more/${index}`).then(resp => {
        //     $scope.products = resp.data;
        // })
    }

    $scope.cart = {
        items: [],
        // Thêm sản phẩm vào giỏ hàng
        add(id) {
            var item = this.items.find(item => item.id == id);
            if (item) {
                item.qty++;
                this.saveToLocalStorage();
            } else {
                $http.get(`/rest/products/${id}`).then(resp => {
                    resp.data.qty = 1;
                    this.items.push(resp.data);
                    this.saveToLocalStorage();
                });
            }
        },
        // Xóa sản phẩm khỏi giỏ hàng
        remove(id) {
            var index = this.items.findIndex(item => item.id == id);
            this.items.splice(index, 1);
            this.saveToLocalStorage();
        },
        //Xóa giỏ hàng
        clear() {
            this.items = [];
            this.saveToLocalStorage();
        },
        //Giảm số lượng sản phẩm trong giỏ hàng
        decrease(id) {
            var item = this.items.find(item => item.id == id);
            if (item.qty > 1) {
                item.qty--;
                this.saveToLocalStorage();
            }
        },
        //Tăng số lượng sản phẩm trong giỏ hàng
        increase(id) {
            var item = this.items.find(item => item.id == id);
            item.qty++;
            this.saveToLocalStorage();
        },
        //Tổng số lượng
        get count() {
            return this.items
                .map(item => item.qty)
                .reduce((total, qty) => total += qty, 0);
        },
        //Tổng tiền
        get amount() {
            return this.items
                .map(item => item.qty * item.price)
                .reduce((total, qty) => total += qty, 0);
        },
        //lưu giỏ hàng xuống localStorage
        saveToLocalStorage() {
            var json = JSON.stringify(angular.copy(this.items));
            localStorage.setItem('cart', json);
        },
        //đọc giỏ hàng từ localStorage
        loadFromLocalStorage() {
            var json = localStorage.getItem('cart');
            this.items = json ? JSON.parse(json) : [];
        }
    };

    $scope.cart.loadFromLocalStorage();

    //order
    $scope.order = {
        createdDate: new Date(),
        address: "",
        username: {username: $( "#username" ).text()},
        get orderDetails() {
            return $scope.cart.items.map(item => {
                return {
                    product: { id: item.id },
                    price: item.price,
                    quantity: item.qty
                }
            });
        },
        amount: $scope.cart.amount,
        purchase() {
            var order = angular.copy(this);
            $http.post(`/rest/orders`, order).then(resp => {
                alert("Đặt hàng thành công");
                $scope.cart.clear();
                location.href = "/order/detail/" + resp.data.id;
            }).catch(error => {
                alert("Lỗi đặt hàng");
                console.log(error);
            });
        }
    }
});