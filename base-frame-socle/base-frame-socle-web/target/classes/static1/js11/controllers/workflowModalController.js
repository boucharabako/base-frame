/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


'use strict';
var App;
var appUrl;
App.controller('workflowModalController', ['$scope', 'GenericService', function ($scope, GenericService) {
        const getCommentRequiredUrl = appUrl + "api/parametre/conf/workflow/action/getCommentRequired";
        $scope.objectWorfkowForward = {};
        $scope.objectWorfkowForwardMaster = {};
        $scope.commentGeneric = "";
        $scope.commentGeneric_anonymose = "N";
        var fonctionGeneric;
        $scope.genericServiceForwardWorkflow = function (m, fonction) {
//            alert("genericServiceForwardWorkflow")
            $scope.commentGeneric = "";
            $scope.commentGeneric_anonymose = "N";
            $scope.verVoyCons = [];
            $scope.verVoyCons = m.actionObject.libelleAction.toLowerCase();
            $('#commentaireGenericLabelText').text(m.actionObject.libelleAction);
            fonctionGeneric = fonction;
            $scope.getCommentRequired(m.actionObject.idActionPermise, m)
        };

        $('#workFlowGenericComment').on('hidden.bs.modal', function () {
            $scope.objectWorfkowForward = angular.copy($scope.objectWorfkowForwardMaster);
            fonctionGeneric = null;
            $('#commentaireGenericText').val('');
            $("#commentaireGenericAnonyme").prop('checked', false);
            var element = document.getElementById("commentaireGenericAnonyme");
            element.setAttribute("checked", false);
        });
        $('#workFlowGenericComment').on('shown.bs.modal', function () {
            $('#commentaireGenericText').val('');
            $("#commentaireGenericAnonyme").prop('checked', false);
            var element = document.getElementById("commentaireGenericAnonyme");
            element.setAttribute("checked", false);
            $scope.commentGeneric = "";
            $scope.commentGeneric_anonymose = "N";
        });

        $('#commentaireGenericAnonyme').on('click', function () {
            if (typeof fonctionGeneric === "function") {
//                alert("function")
                if ($scope.objectWorfkowForward && $scope.objectWorfkowForward != null
                        && $scope.objectWorfkowForward != 'undefined' && $scope.objectWorfkowForward != '') {
                    var element = document.getElementById("commentaireGenericAnonyme");
                    var statut = element.getAttribute("checked");
                    if (statut == true || statut == "true") {
                        element.setAttribute("checked", false);
                    } else {
                        element.setAttribute("checked", true);
                    }
                }
            }
        });

        $('#commentValidateButton').on('click', function () {
//            alert("85" + angular.toJson($scope.objectWorfkowForward))
            if (typeof fonctionGeneric === "function") {
//                alert("function")
                if ($scope.objectWorfkowForward && $scope.objectWorfkowForward != null
                        && $scope.objectWorfkowForward != 'undefined' && $scope.objectWorfkowForward != '') {
//                    alert("OBJET")
                    $scope.commentGeneric = $('#commentaireGenericText').val().trim();
                    var element = document.getElementById("commentaireGenericAnonyme");
                    if ($scope.commentGeneric == 'undefined' || $scope.commentGeneric == '' || $scope.commentGeneric == null) {
                        GenericService.frontValidationMessages("DANGER", "Le commentaire est obligatoire");
                    } else {
                        $scope.commentGeneric_anonymose = (element.getAttribute("checked") == true || element.getAttribute("checked") == "true") ? "O" : "N";

                        fonctionGeneric($scope.objectWorfkowForward);

                        $scope.objectWorfkowForward = angular.copy($scope.objectWorfkowForwardMaster);
                        $('#commentaireGenericText').val('');
                        $("#commentaireGenericAnonyme").prop('checked', false);
                        var element = document.getElementById("commentaireGenericAnonyme");
                        element.setAttribute("checked", false);
                        $('#workFlowGenericComment').modal('hide');
                    }
                }
            }
        });

        $scope.getCommentRequired = function (idActionPermise, m) {
            GenericService.get(getCommentRequiredUrl + "/" + idActionPermise)
                    .then(
                            function (data) {
//                                alert(data.comment);
                                if (data.comment===0) {
                                    showConfirmation($scope.verVoyCons, function () {
                                        fonctionGeneric(m);
                                    });
                                } else if(data.comment===1) {
                                    $scope.objectWorfkowForward = angular.copy($scope.objectWorfkowForwardMaster);
                                    $scope.objectWorfkowForward = angular.copy(m);
                                    $('#workFlowGenericComment').modal('show');
                                }else{
                                     fonctionGeneric(m);
                                }
                            },
                            function () {
                            }
                    );
        };

    }]);
