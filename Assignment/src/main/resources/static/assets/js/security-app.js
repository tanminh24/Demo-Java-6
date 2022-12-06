const app = angular.module('security-app', []);

app.controller('security-ctrl', function($scope, $http) {

    $scope.module = $( "#module" ).text();

});

