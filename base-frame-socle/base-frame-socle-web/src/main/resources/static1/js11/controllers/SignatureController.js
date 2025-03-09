/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 *https://github.com/jagdeep-singh/angularMultipleSelect
 */

/*
 * Pour integrer ce fragment de gestion de cycle de vie via workflow
 * voir le exampleController.js
 */




var App;
var appUrl;
App.controller('SignatureController', ['$scope', 'GenericService', function ($scope, GenericService) {

        var baseUrl = appUrl + 'api/utilisateurs/';

        $scope.sigObjMaster = {"id": null, "titre": null, "textJuridique": null, "textSignature": null, };
        $scope.sigObj = angular.copy($scope.sigObjMaster);


//        $scope.selectedList = [];

        $scope.init = function () {
            jslog("SignatureController");
        };

        $scope.init();
    }]);
