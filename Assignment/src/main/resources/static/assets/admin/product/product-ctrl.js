app.controller("product-ctrl", function ($scope, $http) {
    $scope.items = [];
    $scope.categories = [];
    $scope.form = {};

    $scope.initialize = function () {
        //load products
        $http.get("/rest/products").then(resp => {
            $scope.items = resp.data;
            $scope.items.forEach(item => {
                item.createDate = new Date(item.createDate);
            });
        });
        //load categories
        $http.get("/rest/categories").then(resp => {
            $scope.categories = resp.data;
        });
        //load user login
        $http.get("/rest/accounts/current").then(resp => {
            $scope.account = resp.data;
        });
    }
    $scope.initialize();

    //reset form
    $scope.reset = function () {
        $scope.form = {
            image: 'cloud-upload.jpg'
        };
    }
    $scope.reset();

    //Chi tiết
    $scope.edit = function (item) {
        $scope.form = angular.copy(item);
    }

    //Thêm
    $scope.create = function () {
        $scope.form.available = 1;
        $scope.form.createdDate = new Date();
        $scope.form.createdUser = $scope.account;
        $scope.form.lastModifiedDate= new Date();
        $scope.form.lastModifiedUser = $scope.account;

        var item = angular.copy($scope.form);
        $http.post(`/rest/products`, item).then(resp => {
            $scope.items.push(resp.data);
            $scope.reset();
            alert("Thêm mới thành công!");
        }).catch(error => {
            alert("Lỗi thêm mới sản phẩm!");
            console.log("Error", error);
        });
    }

    //Cập nhật
    $scope.update = function () {
        $scope.form.lastModifiedDate= new Date();
        $scope.form.lastModifiedUser = $scope.account;
        var item = angular.copy($scope.form);
        $http.put(`/rest/products/${item.id}`, item).then(resp => {
            var index = $scope.items.findIndex(p => p.id == item.id);
            $scope.items[index] = item;
            alert("Cập nhật thành công!");
        }).catch(error => {
            alert("Lỗi cập nhật sản phẩm!");
            console.log("Error", error);
        });
    }

    //Unavailable
    $scope.unavailable = function () {
        $scope.form.available = 0;
        $scope.form.lastModifiedDate= new Date();
        $scope.form.lastModifiedUser = $scope.account;
        var item = angular.copy($scope.form);
        $http.put(`/rest/products/${item.id}`, item).then(resp => {
            var index = $scope.items.findIndex(p => p.id == item.id);
            $scope.items[index] = item;
            alert("Cập nhật thành công!");
        }).catch(error => {
            alert("Lỗi cập nhật sản phẩm!");
            console.log("Error", error);
        });
    }

    //available
    $scope.available = function () {
        $scope.form.available = 1;
        $scope.form.lastModifiedDate= new Date();
        $scope.form.lastModifiedUser = $scope.account;
        var item = angular.copy($scope.form);
        $http.put(`/rest/products/${item.id}`, item).then(resp => {
            var index = $scope.items.findIndex(p => p.id == item.id);
            $scope.items[index] = item;
            alert("Cập nhật thành công!");
        }).catch(error => {
            alert("Lỗi cập nhật sản phẩm!");
            console.log("Error", error);
        });
    }

    //Xóa
    $scope.delete = function (item) {
        $http.delete(`/rest/products/${item.id}`).then(resp => {
            var index = $scope.items.findIndex(p => p.id == item.id);
            $scope.items.splice(index, 1);
            $scope.reset(); 
            alert("Xóa thành công!");
        }).catch(error => {
            alert("Lỗi xóa sản phẩm!");
            console.log("Error", error);
        });
    }

    //export excel
    $scope.exportExcel = function () {
        $http.get("/rest/products/export/excel").then(resp => {
            
        }).catch(error => {
            alert("Lỗi xuất excel!");
            console.log("Error", error);
        });
    }

    //Upload file
    $scope.imageChanged = function (files) {
        var data = new FormData();
        data.append('file', files[0]);
        $http.post(`/rest/upload/images`, data, {
            transformRequest: angular.identity,
            headers: {
                'Content-Type': undefined
            }
        }).then(resp => {
            $scope.form.image = resp.data.name;
            console.log(resp.data.name);
        }).catch(error => {
            alert("Lỗi upload hình ảnh!");
            console.log("Error", error);
        });
    }

    //Phân trang
    $scope.pager = {
        page: 0,
        size: 5,
        get items() {
            var start = this.page * this.size;
            return $scope.items.slice(start, start + this.size);
        },
        get count() {
            return Math.ceil(1.0 * $scope.items.length / this.size);
        },
        first() {
            this.page = 0;
        },
        prev() {
            this.page--;
            if (this.page < 0) {
                this.last();
            }
        },
        next() {
            this.page++;
            if (this.page >= this.count) {
                this.first();
            }
        },
        last() {
            this.page = this.count - 1;
        }
    }

    //when click button, click file input
    $scope.btnUploadFile = function () {
        $('#image').trigger('click');
    }

});