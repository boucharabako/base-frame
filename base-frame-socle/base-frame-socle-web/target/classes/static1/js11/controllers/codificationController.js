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
App.controller('codificationController', ['$scope', 'GenericService', function ($scope, GenericService) {

        var baseUrl = appUrl + 'api/utilisateurs/';
        var codificationUrl = appUrl + 'api/codifList/codification';

        $scope.listCodifObjMaster = {"idCodif": null, "libelle": null, "codeCodif": null,
            "multiSelect": null, "selectedListCodifs": []};
        $scope.listCodifObj = angular.copy($scope.listCodifObjMaster);

        $scope.optionsList = [];

//        $scope.selectedList = [];

        $scope.isvalid = function () {
            return $scope.listCodifObj.idCodif != null ||
                    $scope.listCodifObj.selectedListCodifs.length > 0;
        };

        $scope.init = function () {
            $scope.optionsList = [];
            $scope.listCodifObj.selectedListCodifs = [];
            $scope.listCodifObj.idCodif = null;
            $scope.listCodifObj.codeCodif = angular.element("#codeCodif").val();
            $scope.listCodifObj.multiSelect = angular.element("#multiSelect").val();

            jslog("codificationController: " + angular.toJson($scope.listCodifObj));

            if ($scope.listCodifObj.multiSelect != null) {
                GenericService.get(codificationUrl + "/" + $scope.listCodifObj.codeCodif)
                        .then(
                                function (data) {
                                    jslog("codificationController codificationUrl  : " + angular.toJson(data));
                                    $scope.optionsList = data.codifLists;
                                },
                                function (errResponse) {
                                }
                        );

//                $scope.optionsList = [
//                    {id: 1, name: "Java"},
//                    {id: 2, name: "C"},
//                    {id: 3, name: "C++"},
//                    {id: 4, name: "AngularJs"},
//                    {id: 5, name: "JavaScript"}
//                ];
            }

        };

        $scope.init();
    }]);
