app = angular.module('admin-app', ['ngRoute']);

app.config(function ($routeProvider) {
    $routeProvider
        .when('/category', {
            templateUrl: '/assets/admin/category/index.html',
            controller: 'category-ctrl'
        })
        .when('/product', {
            templateUrl: '/assets/admin/product/index.html',
            controller: 'product-ctrl'
        })
        .when('/authorize', {
            templateUrl: '/assets/admin/authority/index.html',
            controller: 'authority-ctrl'
        })
        .when('/unauthorized', {
            templateUrl: '/assets/admin/authority/unauthorized.html',
            controller: 'authority-ctrl'
        })
        .otherwise({
            template: '<h1>Không tìm thấy trang</h1>'
        })
});

app.controller("admin-ctrl", function ($scope, $http) {
    $scope.initialize = function () {
        $http.get("/rest/accounts/current").then(resp => {
            $scope.account = resp.data;
        }).catch(error => {
            alert("Lỗi lấy thông tin người dùng!");
            console.log("Error", error);
        });
    }
    $scope.initialize();
});