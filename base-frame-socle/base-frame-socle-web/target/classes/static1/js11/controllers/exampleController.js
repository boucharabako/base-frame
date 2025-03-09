/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

var App;
var appUrl;
App.controller('exampleController', ['$scope', 'GenericService', function ($scope, GenericService) {

        var fragmentURL = appUrl + 'parametre/conf/workflow';


//Fragment workflow Cycle de vie
        var wfBaseURL = appUrl + 'parametre/conf/workflow/cycle-vie';

        var wfId = '9ba0c8b9-00bd-4d1b-bf7e-da57b351bc6b';
        var wfEtatId = '66fafbdf-9e49-4c9f-875e-7e579e62fefb';
        var natureObjet = 'natureOBj';
        var IdObjet = 'IDOBj';

        $scope.cycleVieURL = wfBaseURL + '/' + wfId + '/' + wfEtatId + '/' + natureObjet + '/' + IdObjet;




        jslog("exampleController : " + angular.toJson($scope.includeURL));


        $scope.avancerCycleVie = function (object, action) {
            // perform operation on this item before selecting it.

            jslog("exampleController: avancerCycleVie " + angular.toJson(object) +
                    " action : " + angular.toJson(action));
        };

//Fragment codification

        var idSig = "idSig";
        $scope.signatureURL = fragmentURL + '/signature/' + idSig;

        var codeCodif = "DOMAINES";
        $scope.codificationMutilpleURL = fragmentURL + '/codif/' + codeCodif + "?m=true";
        $scope.codificationURL = fragmentURL + '/codif/' + codeCodif;


        $scope.validerCodification = function (object) {
            // perform operation on this item before selecting it.
            jslog("exampleController: validerCodification " + angular.toJson(object));
        };

    }]);
