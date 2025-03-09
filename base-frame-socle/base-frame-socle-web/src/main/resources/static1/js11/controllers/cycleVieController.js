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
App.controller('cycleVieController', ['$rootScope', '$scope', 'GenericService', 'PropagationService', function ($rootScope, $scope, GenericService, PropagationService) {

        var baseUrl = appUrl + 'api/utilisateurs/';
        var usersUrl = baseUrl + 'getUsersFromProfils';
        var profilAutoUrl = appUrl + 'api/parameter/autorisation/wf/stepAction';
        var initWorkflowURL = appUrl + 'api/parametre/fragment/conf/workflow-cycle/init-workflow-object';
        var wfAvancerObjURL = appUrl + 'api/parametre/fragment/conf/workflow-cycle/executer-action';
//        var wfAvancerObjProgrammeURL = appUrl + 'api/parameter/ref/budgetaire/programme/executer-action-programme';
//        var wfAvancerObjLoiURL = appUrl + 'api/parameter/ref/budgetaire/cadre/lois/executer-action-loi';
        $scope.urlOperation = $("#urlOperation").val();

        $scope.wfStepObjMaster = {"idobj": null, "idStep": null,
            "comment": null, "action": null, "nextStep": null, "notifyUsers": false,
            selectedUsers: [], conceptMetier: null};
        $scope.wfStepObj = angular.copy($scope.wfStepObjMaster);

        $scope.item = null;
        $scope.userList = [];
//        $scope.optionsList = [];

//        $scope.profiles = ["ADMIN", "USERGED"];
        $scope.profiles = [];


        $scope.workflowCycleParameter = {};
        $scope.workflowObjet = {};


        $rootScope.initParameters = function (idObjet, conceptMetier, natureObjet, executor, paramsExecutor) {

            if (executor) {
                $scope.objectParams.executor = executor;
                $scope.objectParams.paramsExecutor = paramsExecutor;
            }
            $scope.objectParams.conceptMetier = executor;
            $scope.objectParams.idObjet = executor;
            $scope.objectParams.natureObjet = executor;
            $scope.initWorkflowParams();

        };

        $scope.getUsers = function () {
            GenericService.post(usersUrl, angular.toJson($scope.profiles))
                    .then(
                            function (data) {
                                jslog("cycleVieController getUsers  : " + angular.toJson(data));
                                $scope.userList = data.users;
                            },
                            function (errResponse) {
                            }
                    );
        };

        
        $scope.getProfileAutorisationNextStep = function (actionId) {
            jslog("actionId: " + angular.toJson(actionId));
            GenericService.post(profilAutoUrl, actionId)
                    .then(
                            function (data) {
                                $scope.profiles = [];
                                $scope.userList = [];
                                $scope.wfStepObj.selectedUsers = [];

                                jslog("getProfileAutorisation  : " + angular.toJson(data));
                                angular.forEach(data.listAutorisations, function (value, key) {
                                    this.push(value.ideObjet);
                                }, $scope.profiles);
                                jslog("$scope.profiles  : " + angular.toJson($scope.profiles));

                                if ($scope.profiles.length > 0) {
                                    GenericService.post(usersUrl, angular.toJson($scope.profiles))
                                            .then(
                                                    function (data) {
                                                        jslog("cycleVieController getUsers  : " + angular.toJson(data));
                                                        $scope.userList = data.users;
                                                        //alert();
                                                    },
                                                    function (errResponse) {
                                                    }
                                            );

                                } else {
                                    GenericService.frontValidationMessages("WARNING", " Cette action aboutira à une terminaison du cycle de vie ",
                                            "L'action n'a pu être exécutée, car il s'est produit des erreurs de validation");
                                }

                            },
                            function (errResponse) {
                            }
                    );
        };

        $scope.getUsers = function () {
            GenericService.post(usersUrl, angular.toJson($scope.profiles))
                    .then(
                            function (data) {
//                                jslog("cycleVieController getUsers  : " + angular.toJson(data));
                                $scope.userList = data.users;
                            },
                            function (errResponse) {
                            }
                    );
        };

//        $scope.selectedList = [];

//        $scope.isvalid = function () {
//            return $scope.userList.length > 0 ||
//                    $scope.wfStepObj.selectedUsers.length > 0;
//        };
        $scope.isvalid = function () {
            return $scope.userList.length > 0;
        };
        $scope.init = function () {
            jslog("cycleVieController");
            $scope.profiles = [];
            $scope.userList = [];
            $scope.wfStepObj = angular.copy($scope.wfStepObjMaster);

//            $scope.wfStepObj.nobj = angular.element("#nobj").val();
            $scope.wfStepObj.idobj = angular.element("#idobj").val();
            $scope.wfStepObj.idStep = angular.element("#idStep").val();
            $scope.wfStepObj.action = angular.element("#OneAction").val();
            $scope.wfStepObj.conceptMetier = angular.element("#conceptMetier").val();
            $scope.wfStepObj.codeFonction = angular.element("#codeFonction").val();

            jslog("init wfStepObj.action: " + angular.toJson($scope.wfStepObj));
            if ($scope.wfStepObj.action != null) {
                jslog("entree recherche user");
                $scope.selectWfAction($scope.wfStepObj.action);
            }


            jslog("init: " + angular.toJson($scope.wfStepObj));

            $scope.initParams();

        };

        $scope.initParams = function () {
            $scope.workflowCycleParameter.concepMetier = $scope.wfStepObj.conceptMetier;
            $scope.workflowCycleParameter.idObjet = $scope.wfStepObj.idobj;
            jslog("initParams $scope.wfStepObj.codeFonction: " + $scope.wfStepObj.codeFonction);
            if ($scope.wfStepObj.codeFonction) {
                $scope.workflowCycleParameter.codeFonction = $scope.wfStepObj.codeFonction;
            }

            jslog("initParams workflowCycleParameter  : " + angular.toJson($scope.workflowCycleParameter));

            GenericService.post(initWorkflowURL, angular.toJson($scope.workflowCycleParameter))
                    .then(
                            function (data) {
                                jslog("initWorkflowURL1  : " + angular.toJson(data));
                                $scope.workflowObjet = data;
                                if($scope.workflowObjet.actions.length==1){
                                    $scope.selectWfAction($scope.workflowObjet.actions[0].id);
                                }
                            },
                            function (errResponse) {
                                jslog("initWorkflowURL errResponse : " + angular.toJson(errResponse));
                            }
                    );
        };
        $scope.initWorkflowParams = function () {
            $scope.workflowCycleParameter.concepMetier = $scope.objectParams.conceptMetier;
            $scope.workflowCycleParameter.idObjet = $scope.objectParams.idObjet;

            jslog("initParams workflowCycleParameter  : " + angular.toJson($scope.workflowCycleParameter));

            GenericService.post(initWorkflowURL, angular.toJson($scope.workflowCycleParameter))
                    .then(
                            function (data) {
                                jslog("initWorkflowURL2  : " + angular.toJson(data));
                                $scope.workflowObjet = data;
                            },
                            function (errResponse) {
                                jslog("initWorkflowURL errResponse : " + angular.toJson(errResponse));
                            }
                    );
        };



        $scope.selectWfAction = function (actionId) {
            // perform operation on this item before selecting it.
//            $scope.getUsers();
            jslog("selectWfAction: " + angular.toJson($scope.wfStepObj) + " actionId: " + actionId);

            $scope.getProfileAutorisationNextStep(actionId);

        };


        $scope.avancerCycleVie = function (object, action) {
            jslog("cycleVieController: avancerCycleVie " + angular.toJson(object) +
                    " action : " + angular.toJson(action));

            $scope.workflowCycleParameter = {};
            $scope.workflowCycleParameter.concepMetier = object.conceptMetier;
            if ($scope.execution) {
                $scope.workflowCycleParameter.executor = $scope.execution.executor;
                $scope.workflowCycleParameter.paramsExecutor = $scope.execution.paramsExecutor;
            }

            $scope.workflowCycleParameter.executor = "gedExecutor";
            $scope.workflowCycleParameter.paramsExecutor = {id: "TCI-000230", montant: 20000000, nom: "William", prog: {id: "IMT-3200", libelle: "Executor test"}};





            $scope.workflowCycleParameter.idObjet = object.idobj;
            $scope.workflowCycleParameter.idAction = (object.action) ? object.action : angular.element("#OneAction").val();
            $scope.workflowCycleParameter.commentaire = object.comment;
            $scope.workflowCycleParameter.users = [];

            angular.forEach(object.selectedUsers, function (value, key) {
                this.push(value.username);
            }, $scope.workflowCycleParameter.users);

            jslog("cycleVieController workflowCycleParameter  : " + angular.toJson($scope.workflowCycleParameter));

            GenericService.post(wfAvancerObjURL, angular.toJson($scope.workflowCycleParameter))
                    .then(
                            function (data) {
                                jslog("cycleVieController wfAvancerObjURL  : " + angular.toJson(data));
                                // Recharger la page
//                                window.location.reload();
                                //Diffuser un message pour indiquer la fin de l'operation en cours
                                PropagationService.setOperation(true);
                                PropagationService.setObject($scope.workflowCycleParameter);
                                $scope.init();
                                delete  $scope.execution;
                            },
                            function (errResponse) {
                                jslog("errResponse: " + angular.toJson(errResponse));
                            }
                    );

//           
        };

        $scope.avancerCycleVieForParentAndChildrenOrParentOnly = function (object, action) {
            jslog("*-*-*-URL CYCLE DE VIE PROGRAMME*-*-*-* " + $scope.urlOperation);
            showConfirmation("effectuer cette opération", function () {
                $scope.workflowCycleParameter = {};
                $scope.workflowCycleParameter.concepMetier = object.conceptMetier;
                $scope.workflowCycleParameter.idObjet = object.idobj;
                $scope.workflowCycleParameter.idAction = (object.action) ? object.action : angular.element("#OneAction").val();
                $scope.workflowCycleParameter.commentaire = object.comment;
                $scope.workflowCycleParameter.users = [];

                angular.forEach(object.selectedUsers, function (value, key) {
                    this.push(value.username);
                }, $scope.workflowCycleParameter.users);

                GenericService.post($scope.urlOperation, angular.toJson($scope.workflowCycleParameter))
                        .then(
                                function (data) {
                                    //Diffuser un message pour indiquer la fin de l'operation en cours
                                    if (data.check == true) {
                                        $scope.question($scope.workflowCycleParameter);
                                    } else {
                                        PropagationService.setOperation(true);
                                        $scope.init();
                                    }
                                },
                                function (errResponse) {
                                    jslog("errResponse: " + angular.toJson(errResponse));
                                }
                        );
            });
        };

        $scope.question = function (object) {
//            jslog("555555");
            $('#ltc-modal4').modal('show');
            PropagationService.setObject(object);
        };

        $scope.beforeSelectItem = function (item) {
            // perform operation on this item before selecting it.
            $scope.item = item;
            jslog("beforeSelectItem: " + angular.toJson(item));
        };

        $scope.afterSelectItem = function (item) {
            // perform operation on this item after selecting it.
            $scope.item = item;
            jslog("afterSelectItem" + angular.toJson(item));
        };

        $scope.beforeRemoveItem = function (item) {
            // perform operation on this item before removing it.
            $scope.item = item;
            jslog("beforeRemoveItem" + angular.toJson(item));
        };

        $scope.afterRemoveItem = function (item) {
            // perform operation on this item after removing it.
            $scope.item = item;
            jslog("afterRemoveItem" + angular.toJson(item));
        };

//        $('wkf-modal')
        $('#wkf-modal').on('hidden.bs.modal', function () {
            // do something…
            $scope.init();
        });

        $scope.init();
    }]);
