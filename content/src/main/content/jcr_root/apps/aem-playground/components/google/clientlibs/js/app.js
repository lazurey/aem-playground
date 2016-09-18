var playgroundApp = angular.module('my-app', []);

playgroundApp.controller('NameController', function NameController($scope) {
  $scope.name = 'type your name';
});
