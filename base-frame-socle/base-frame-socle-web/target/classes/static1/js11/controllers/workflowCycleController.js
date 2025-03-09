/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

'use strict';
var App;
var appUrl;
App.controller('workflowCycleController', ['$scope', 'GenericService', 'authorityService', function ($scope, GenericService, authorityService) {


        var urlDeBase = appUrl + "api/parametre/conf/workflow-cycle";

        var urlCodification = appUrl + "api/codifList/codification/";

        $scope.modele = {};
//        $scope.page = {first: 0, perv: -1, next: 1, last: 1};
        $scope.page = {index: -1};
        $scope.twk = '';
        $scope.motCle = '';
        $scope.etapes = [];
        $scope.typeWorkflow = {code: 'WU', libelle: null};
        $scope.selected = {};
        $scope.listeWorkflowCycles = [];
        $scope.selectedWorkFLow = null;
        $scope.WU = {};
        $scope.WS = {};
        $scope.conceptesMetiers = [];
        $scope.callbacks = [];
        $scope.conceptesMetiersWkf = [];
        $scope.conceptesSelected = [];
        $scope.codifHabilitation = null;
        $scope.habilitations = [];
        $scope.listEventParam = [];

        $scope.findAllWorkflowCycle = function (mc) {

            GenericService.get(urlDeBase + "/find-all?t=" + $scope.typeWorkflow.code + "&m=" + mc)
                    .then(
                            function (data) {
                                $scope.listeWorkflowCycles = data.content;
                                $scope.etapes = [];
                                delete      $scope.wk;
                                delete     $scope.ws;
                                if ($scope.listeWorkflowCycles.length > 0) {
                                    $scope.page.index = 0;

                                    $scope.moove($scope.page.index);

                                }

                            },
                            function () {

                            }
                    );
        };

        $scope.findAllWorkflowCycle('', '');

        $scope.findAllType = function () {
            $scope.listeTypeWorkflows = [];
            GenericService.get(urlDeBase + "/find-all-type")
                    .then(
                            function (data) {

                                $scope.listeTypeWorkflows = data;



                                data.forEach(function (e) {
                                    if (e.code === "WU") {
                                        $scope.twk = e.code;
                                        $scope.typeWorkflow = e;
                                        $scope.WU = e;
                                    }
                                    if (e.code === "WS") {
                                        $scope.WS = e;
                                    }
                                });

                                if ($scope.listeTypeWorkflows && $scope.listeTypeWorkflows.length > 0 && $scope.listeTypeWorkflows[0]) {
                                    $scope.typeWorkflow.code = $scope.listeTypeWorkflows[1].code;
                                    jslog("bb " + $scope.typeWorkflow.code);
                                    $scope.findAllWorkflowCycle("");
                                }

                            },
                            function () {

                            }
                    );
        };
        $scope.findAllType();

        /**
         * 
         * @returns {undefined}
         */
        $scope.findListeAction = function () {

            GenericService.get(urlDeBase + "/find-all-liste-action")
                    .then(
                            function (data) {
//                                jslog("LISTE ACTION " + angular.toJson(data))
                                $scope.listeActions = data;

                                $scope.codifHabilitation = $('#codifHabilitation').val();
                                jslog("findListeAction $scope.codifHabilitation: " + $scope.codifHabilitation);
                                if ($scope.codifHabilitation != null) {
                                    GenericService.get(urlCodification + $scope.codifHabilitation)
                                            .then(
                                                    function (data) {
                                                        jslog("findListeAction liste habilitations : " + angular.toJson(data));
                                                        $scope.habilitations = data.codifLists;
                                                    },
                                                    function () {

                                                    }
                                            );
                                }
                            },
                            function () {

                            }
                    );
        };

        $scope.findListeAction();

        $scope.selectWorkflow = function (index) {
            $scope.modele = $scope.listeWorkflowCycles[index];



        };

        $scope.moove = function (index) {
            delete $scope.ws;
            delete $scope.wk;
            if (index >= 0) {
                if ($scope.listeWorkflowCycles.length - 1 >= index) {
                    $scope.listeWorkflowCycleSelected = $scope.listeWorkflowCycles[index];
                    $scope.page.index = index;
                    $scope.etapes = [];
                    $scope.conceptesSelected = [];

                    $scope.conceptesMetiersWkf = [];

                    $scope.initEtape(index);

                }
            }

        };

        $scope.boolean = function (val) {


            if (val < 0) {
                return true;
            } else {
                return false;
            }
            ;

        };
        $scope.initNewWorkflow = function (index) {
            $scope.modele = {};
            $scope.modele.typeWorkflow = $scope.typeWorkflow;
            $scope.modele.numero = $scope.typeWorkflow.dernierNumero + 1;
            $scope.modele.intension = 'E';
            delete $scope.modele.actionEditable;
        };

        $scope.initNewWorkflowNum = function (index) {

            GenericService.get(urlDeBase + "/find-last-numero")
                    .then(
                            function (data) {

                                $scope.lastNumero = data.numero;
                                $scope.initNewWorkflow(index)

                            },
                            function () {

                            }
                    );
        };
        $scope.initUpdateWorkflow = function (index, intention) {
            $scope.modele = $scope.listeWorkflowCycles[index];
            GenericService.get(urlDeBase + "/count-nombre-etape/" + $scope.modele.id)
                    .then(
                            function (data) {

                                if (data > 0) {
                                    $scope.modele.actionEditable = true;

                                    $scope.listeActions.forEach(function (e) {
                                        if (e.code === $scope.modele.listeAction) {
                                            $scope.modele.libelleAction = e.libelle;
                                            return;
                                        }
                                    });
                                }


                            },
                            function () {

                            }
                    );

            $scope.modele.intension = intention;


        };
        $scope.initTypeWorkflow = function (code) {

            $scope.listeTypeWorkflows.forEach(function (e) {
                if (e.code == code) {

                    $scope.typeWorkflow = e;
                    $scope.findAllWorkflowCycle("");
                }
            });
        };

        /**
         * Enregistrement d'un nouveau workflow ou mise à jours
         * @returns {undefined}
         */
        $scope.saveOne = function () {
            if ($scope.typeWorkflow) {
                $scope.modele.codeTypeWorkflow = $scope.modele.typeWorkflow.code;
            }
            delete $scope.modele.typeWorkflow;
            delete $scope.modele.statutDto;


            GenericService.post(urlDeBase + "/save-one", $scope.modele)
                    .then(
                            function (data) {

                                if (data.msg.typeError === "INFO") {

                                    var mm = $scope.modele;

                                    $scope.modele = data.value;



                                    if (mm.actionEditable) {
                                        $scope.modele.actionEditable = mm.actionEditable;
                                        $scope.modele.libelleAction = mm.libelleAction;

                                    }
                                    if (data.flag) {
                                        $scope.etapes = [];
                                        $scope.listeWorkflowCycles.push(data.value);
                                        $scope.page.index = $scope.listeWorkflowCycles.length - 1;
                                        $scope.conceptesMetiersWkf = [];
                                        $scope.initNewEtape();
                                        $scope.wk = 1;
                                        $scope.listeTypeWorkflows.forEach(function (e) {
                                            if (e.code === $scope.modele.typeWorkflow.code) {
                                                e.dernierNumero = $scope.modele.typeWorkflow.dernierNumero;
                                            }
                                            $scope.selectedWorkFLow = $scope.modele.id;
                                            $scope.loadListActions($scope.selectedWorkFLow);
                                        });

                                    } else {

                                        $scope.listeWorkflowCycles.splice($scope.page.index, 1, data.value);



                                    }
                                    $('#ltc-modal1').modal('toggle');

                                }
                            },
                            function () {

                            }
                    );
            $scope.modele.typeWorkflow = $scope.typeWorkflow;
        };


        $scope.page = {
            last: function () {
                if ($scope.page.index < 0) {
                    return -1;
                }
                if ($scope.page.index === $scope.listeWorkflowCycles.length - 1) {
                    return -1;
                } else {
                    return $scope.listeWorkflowCycles.length - 1;
                }
            },
            disableL: function () {
                return  $scope.boolean($scope.page.last());
            },
            first: function () {

                if ($scope.page.index <= 0 || $scope.listeWorkflowCycles.length === 0) {
                    return -1;
                } else {
                    return 0;
                }
            }
            , disableF: function () {
                return  $scope.boolean($scope.page.first());
            },
            prev: function () {

                if ($scope.page.index <= 0 || $scope.listeWorkflowCycles.length === 0) {
                    return -1;
                } else {
                    return $scope.page.index - 1;
                }
            },
            disableP: function () {
                return  $scope.boolean($scope.page.prev());
            },
            next: function () {
                if ($scope.page.index < 0 || $scope.listeWorkflowCycles.length === 0) {
                    return -1;
                }
                if ($scope.page.index >= $scope.listeWorkflowCycles.length - 1) {
                    return -1;
                } else {
                    return $scope.page.index + 1;
                }
            },
            disableN: function () {
                return  $scope.boolean($scope.page.next());
            }
        };


        $scope.remove = function (index) {
            $scope.modele = {};
            $scope.modele.id = $scope.listeWorkflowCycles[index].id;
            showConfirmation("supprimer", function () {
                GenericService.post(urlDeBase + "/delete-one", $scope.modele)
                        .then(
                                function (data) {
                                    ;
                                    if (data.msg.typeError === "INFO") {

                                        $scope.listeWorkflowCycles.splice($scope.page.index, 1);
                                        $scope.page.index = 0;

                                        $scope.etapes = [];
                                        $scope.moove($scope.page.index);
                                    }
                                },
                                function () {

                                }
                        );
            }, (" Cette action entraînera la suppression des états et leurs actions. "));
        };
        $scope.changerStatut = function (index) {
            $scope.modele = $scope.listeWorkflowCycles[index];
            $scope.modele.statutDto.id = $scope.modele.id;
            showConfirmation($scope.modele.statutDto.libelleStatutSuivant.toLowerCase(), function () {
                GenericService.post(urlDeBase + "/changer-statut", $scope.modele.statutDto)
                        .then(
                                function (data) {
                                    ;
                                    if (data.msg.typeError === "INFO") {
                                        $scope.modele.statutDto = data.value;
                                        $scope.listeWorkflowCycles.splice($scope.page.index, 1, $scope.modele);
                                        delete    $scope.ws;
                                    }
                                },
                                function () {

                                }
                        );
            });
        };
        $scope.checked = function (v1, v2) {
            if (v1 === true && v2 === true) {
                return true;
            } else {
                return false;
            }
        };

        $scope.initEtape = function (index) {
            $scope.modele = $scope.listeWorkflowCycles[index];
            console.info($scope.modele.id);
            GenericService.get(urlDeBase + "/find-all-etape/" + $scope.modele.id)
                    .then(
                            function (data) {

                                $scope.etapes = data;

                                if ($scope.listeWorkflowCycles.length > 0 && $scope.listeWorkflowCycles[$scope.page.index].statutDto.editable === true) {
                                    $scope.ws = 1;
                                } else {
                                    delete $scope.ws;
                                }
                                $scope.wk = 1;
                                $scope.loadListActions($scope.modele.id);
                                $scope.selectedWorkFLow = $scope.modele.id;
                            },
                            function () {

                            }
                    );
            GenericService.get(urlDeBase + "/find-conceptes-metiers/" + $scope.modele.id)
                    .then(
                            function (data) {
                                $scope.conceptesMetiersWkf = data;


                            },
                            function () {

                            }
                    );
        };

        $scope.saveOneEtape = function (index) {
            $scope.etape = $scope.etapes[index];

            console.log($scope.etape);
            $scope.selected.codeWorkflow = $scope.listeWorkflowCycles[$scope.page.index].id;

            GenericService.post(urlDeBase + "/save-one-etat", $scope.selected)
                    .then(
                            function (data) {
                                ;
                                if (data.msg.typeError === "INFO") {
                                    $scope.etapes.splice(index, 1, data.value);
                                    $scope.selected = {};
                                    $scope.wk = 1;

                                    if (data.flag) {
                                        $scope.ws = 1;
                                    }
                                }
                            },
                            function () {

                            }
                    );

        };
        $scope.deleteOneEtat = function (index) {
            $scope.etape = $scope.etapes[index];
            showConfirmation("supprimer", function () {
                GenericService.post(urlDeBase + "/delete-one-etat", $scope.etape)
                        .then(
                                function (data) {
                                    ;
                                    if (data.msg.typeError === "INFO") {
                                        $scope.etapes.splice(index, 1);
                                        $scope.etape = {};
                                    }
                                },
                                function () {

                                }
                        );
            }, (" Cette action entraînera la suppression  des actions. "));
        };

        $scope.initNewEtape = function () {

            if ($scope.etapes.length <= 0 || $scope.etapes[0].id !== '') {
                $scope.etapes.splice(0, 0, {id: '', ideSequence: '', ideEtape: '', codeEtat: '', codeCouleur: '#8e3333', newe: 1, modif: false, etapeValidation: false});
                $scope.ws = 2;
            }

        };
        $scope.viewNewEtat = function () {
            if (!$scope.listeWorkflowCycles[$scope.page.index]) {
                return false;
            }
            if ($scope.listeWorkflowCycles[$scope.page.index].statutDto.editable === false) {
                return false;
            }

            if ($scope.ws === 2) {
                return false;
            } else {
                return true;
            }

        };
        // gets the template to ng-include for a table row / item
        $scope.getTemplate = function (page) {

            if (page.newe) {
                $scope.selected = page;
                return 'edit';
            }

            if (page.id === $scope.selected.id || page.newe)
                return 'edit';
            else
                return 'display';
        };

        $scope.editEtape = function (contact) {
            $scope.selected = angular.copy(contact);
        };
        $scope.reset = function () {
            if ($scope.selected.newe) {
                $scope.etapes.splice(0, 1);
                $scope.ws = 1;
            }
            $scope.selected = {};
        };

        $scope.addWorkflowC = function () {
            if ($scope.twk === '' || $scope.twk === 'WS')
                return false;
            return true;
        };


//        $scope.tp = function (id) {
//            doGet(("parametre/conf/workflow-cycle/" + id),
//                    function (data)
//                    {
//
//                        var res = data.replace("<li>", "");
//                        //    $("#"+id).html(res.replace("</li>",""));
//
//                        return res.replace("</li>", "");
//                    },
//                    function (error)
//                    {
//                        console.info(error);
//                    });
//        };
//        $scope.display = $scope.tp("display");
//        $scope.edit = $scope.tp("edit");




        $scope.deleteOneObjet = function (index) {

            showConfirmation("supprimer", function () {
                $scope.modele.idObject = $scope.conceptesMetiersWkf[index].id;

                console.info("bbbbbb " + $scope.modele.idObject);
                GenericService.post(urlDeBase + "/delete-one-objet", $scope.modele)
                        .then(
                                function (data) {
                                    ;
                                    if (data.msg.typeError === "INFO") {
                                        $scope.conceptesMetiersWkf.splice(index, 1);

                                    }
                                },
                                function () {

                                }
                        );
            });
        };
        $scope.addMultiObjet = function () {
            $scope.modele.listeObjets = [];

            $scope.conceptesSelected.forEach(function (e) {
                $scope.modele.listeObjets.push(e.id);
            });
            GenericService.post(urlDeBase + "/save-liste-object", $scope.modele)
                    .then(
                            function (data) {
                                ;
                                if (data.msg.typeError === "INFO") {

                                    $scope.modele.listeObjets = [];
                                    $scope.listeObjets = [];
                                    $scope.conceptesSelected = [];

                                    $scope.conceptesMetiersWkf = data.value;
                                    $('#wkf-objet').modal('toggle');
                                }
                            },
                            function () {

                            }
                    );

        };
        $scope.findAllConcept = function () {
            $scope.modele.listeObjets = $scope.listeObjets;
            $scope.conceptesSelected = [];

            GenericService.get(urlDeBase + "/find-conceptes-metiers-not")
                    .then(
                            function (data) {
                                $scope.conceptesMetiers = data;
                            },
                            function () {

                            }
                    );

        };


//===============================PIERRE===============================================

        //Constants
        const listActionUrl = appUrl + "api/parametre/conf/workflow/action/listActionProvider";
        const listActionPermiseUrl = appUrl + "api/parametre/conf/workflow/action/listActionPermiseProvider";
        const listEvenementProgrammeAndProfilUrl = appUrl + "api/parametre/conf/workflow/action/listEvenementProgrammeAndProfil";
        const selectedEtatUrl = appUrl + "api/parametre/conf/workflow/action/selectedEtat";
        const listEtatSuivantUrl = appUrl + "api/parametre/conf/workflow/action/currentStep/";
        const listProfilesUrl = appUrl + "api/parametre/conf/workflow/action/listProfilProvider";
        const listExecutorUrl = appUrl + "api/parametre/conf/workflow/action/listExecutorProvider";
        const listEvenementsUrl = appUrl + "api/parametre/conf/workflow/action/listEventProvider";
        const saveActionUrl = appUrl + "api/parametre/conf/workflow/action/addOrUpdateActionToWorkFlow";
        const cancelActionUrl = appUrl + "api/parametre/conf/workflow/action/cancelAction";
        const deleteActionUrl = appUrl + "api/parametre/conf/workflow/action/deleteActionWorkFlow";
        const listcallBack = appUrl + "api/parametre/conf/workflow/action/find-all-callback";
        const listInfoTransionUrl = appUrl + "api/utils/codification/";
        const INFO_TRANSITION = 'INFO_TRANSITION';
        var firstPage = 1;
        var initialNumerOfElements = 5;
        $scope.fivePerPage = initialNumerOfElements;
        $scope.pageSizeSelect;
        $scope.pageSizes = [];
        $scope.sequence = [];
        $scope.selectedPageSize = null;
        $scope.paginationObject = {selectedPageSize: ""};
        $scope.pager = {startPage: "", endPage: "", totalPages: "", currentPage: "", buttonsToShow: ""};
        $scope.actionPage = {number: ""};

        //Variables
        $scope.listActions = [];
        $scope.listEtatSuivant = [];
        $scope.listProfiles = [];
        $scope.listExecutor = [];
        $scope.listProfileSelected = [];
        $scope.listExecutorSelected = [];
        $scope.listEvenements = [];
        $scope.listEvenementSelected = [];
        $scope.listWorkFlowActions = [];
        $scope.displayAddActionButton = true;
        $scope.modeActif = false;
        $scope.admin = false;
        $scope.listInfosTransition = []

        $scope.evenementProgramme = {id: null, idAction: null, idEvent: null, idWorkFlow: null};

        $scope.setSelectedEtape = function (selectedEtape) {
            $scope.selectedEtat = selectedEtape;
            $scope.listEvenementSelected = [];
            $scope.listProfileSelected = [];
            $scope.listExecutorSelected = [];
            $scope.loadListEtatSuivant(selectedEtape.id);
            $scope.listWorkFlowActions = [];
            $scope.loadPaginateActionPermise(selectedEtape.id);
            $scope.loadListProfiles();
            $scope.loadListExecutor();
            $scope.loadListEvenements();
            $scope.displayAddActionButton = true;
        };

        $scope.loadOtherValue = function (action) {

            $scope.listEvenementSelected = [];
            $scope.listProfileSelected = [];
            $scope.listExecutorSelected = [];
            if (action.id !== null) {
                GenericService.get(listEvenementProgrammeAndProfilUrl + "/" + action.id)
                        .then(
                                function (data) {
                                    $scope.listEvenementSelected = data.listEvenement;
                                    $scope.listEventParam = data.listEventParam;
                                    $scope.listProfileSelected = data.listProfileAutorise;
                                    $scope.listExecutorSelected = data.listExecutorSelected;
                                },
                                function () {
                                }
                        );
            }
            $scope.disabledClassifDiv();
        };

        /**
         * 
         * @param {type} idworkFlow
         * @returns {undefined}
         */
        $scope.loadListActions = function (idworkFlow) {
            GenericService.get(listActionUrl + "/" + idworkFlow)
                    .then(
                            function (data) {
                                $scope.listActions = data.listActions;
                            },
                            function () {
                            }
                    );
        };
        /**
         * 
         * @param {type} idworkFlow
         * @returns {undefined}
         */
        $scope.loadListInfoTransition = function (idworkFlow) {
            GenericService.get(listInfoTransionUrl + INFO_TRANSITION)
                    .then(
                            function (data) {
                                $scope.listInfosTransition = data;
                            },
                            function () {
                            }
                    );
        };

        $scope.loadListInfoTransition();
        $scope.findCallBack = function () {

            GenericService.get(listcallBack)
                    .then(
                            function (data) {
//                                jslog("LISTE ACTION " + angular.toJson(data))
                                $scope.callbacks = data;
                            },
                            function () {

                            }
                    );
        };
        $scope.findCallBack();

        /**
         * 
         * @param {type} idEtatCourant
         * @returns {undefined}
         */
        $scope.loadListEtatSuivant = function (idEtatCourant) {
            GenericService.get(listEtatSuivantUrl + idEtatCourant)
                    .then(
                            function (data) {
                                $scope.listEtatSuivant = data.listEtatSuivant;
                            },
                            function () {
                            }
                    );
        };

        /**
         * Retourne 
         * @returns {listProfils}
         */
        $scope.loadListProfiles = function () {
            GenericService.get(listProfilesUrl)
                    .then(
                            function (data) {
                                $scope.listProfiles = data.listProfil;
                            },
                            function () {
                            }
                    );
        };
        $scope.loadListExecutor = function () {
            GenericService.get(listExecutorUrl)
                    .then(
                            function (data) {
                                $scope.listExecutor = data.listExecutor;
                            },
                            function () {
                            }
                    );
        };


        /**
         * Retourne 
         * @returns {listEvenements}
         */
        $scope.loadListEvenements = function () {
            GenericService.get(listEvenementsUrl)
                    .then(
                            function (data) {
                                $scope.listEvenements = data.listEvent;
                            },
                            function () {
                            }
                    );
        };


        /**
         * Enregistre une action d'un
         * @returns {undefined}
         */
        $scope.addAction = function () {
            console.info("dddd " + $scope.selectedWorkFLow)
            $scope.listEvenementSelected = [];
            $scope.listProfileSelected = [];
            $scope.listExecutorSelected = [];

            if ($scope.selectedWorkFLow) {
                $scope.workFlowActionObject =
                        {id: null, idWorkFlow: $scope.selectedWorkFLow,
                            action: null, fond: $scope.selectedEtat.codeCouleur, text: null,
                            etatSelected: null, idCallback: null,
                            etatSuivant: null, listEvenementProgramme: [],
                            listProfileAutorise: [], enabledButton: false, displaySaveButton: true, displayCancelButton: true, displayDeleteButton: true, codeInfoTransition: '141.1.0.0'
                        };


                //if ($scope.workFlowActionObject.action.id !== null) {
                $scope.workFlowActionObject.etatSelected = $scope.selectedEtat.id;
                $scope.workFlowActionObject.codeHabilitation = "1";
                $scope.listWorkFlowActions.splice(0, 0, angular.copy($scope.workFlowActionObject));
                // }
                $scope.listWorkFlowActions = DuplicateFilter($scope.listWorkFlowActions, "action");
                $scope.displayAddActionButton = false;
            }
            $scope.enabledClassifDiv();
        };

        /**
         * Duplicate filter
         * @param {type} collection
         * @param {type} keyname
         * @returns {Array}
         */
        function DuplicateFilter(collection, keyname) {
            var output = [],
                    keys = [];
            angular.forEach(collection, function (item) {
                var key = item[keyname];
                if ((keys.indexOf(key) === -1)) {
                    keys.push(key);
                    output.push(item);
                }
            });
            return output;
        }
        ;
        $scope.saveAction = function (actionToSave) {
            actionToSave.listEvenementProgramme = $scope.listEvenementSelected;
            actionToSave.listProfileAutorise = $scope.listProfileSelected;
            actionToSave.listExecutorSelected = [];
            actionToSave.listExecutorSelected = $scope.listExecutorSelected;

//            angular.forEach($scope.listExecutorSelected, function (k, v) {
//                k.numeroOrdre = angular.copy(v);
//                jslog("V "+angular.toJson(k))
//                actionToSave.listExecutorSelected.splice(v, 0, angular.copy(k));
//            });

            actionToSave.listeParamEvenement = $scope.listEventParam;
//            jslog("DATA " + angular.toJson(actionToSave.listExecutorSelected));
//            return;

            var i = $scope.listWorkFlowActions.indexOf(actionToSave);
            if (i != -1) {
                if ($scope.listWorkFlowActions[i].action !== null) {
                }
                GenericService.post(saveActionUrl, angular.toJson($scope.listWorkFlowActions[i]))
                        .then(
                                function (data) {
                                    if (data.msg.statut !== '400') {
                                        $scope.listWorkFlowActions[i].action = data.savedAction;
                                        $scope.loadPaginateActionPermise($scope.selectedEtat.id);
                                        $scope.displayAddActionButton = true;
                                        $scope.disabledClassifDiv();
                                    }
                                },
                                function () {}
                        );
            }
        };
        $scope.cancelAction = function (actionToCancel) {

            var i = $scope.listWorkFlowActions.indexOf(actionToCancel);
            if (i != -1) {
                $scope.displayAddActionButton = true;
                if ($scope.listWorkFlowActions[i].id != null) {
                    GenericService.get(cancelActionUrl + "/" + $scope.listWorkFlowActions[i].id)
                            .then(
                                    function (data) {
                                        $scope.listWorkFlowActions[i].id = data.actionToCancel.id;
                                        $scope.listWorkFlowActions[i].action = data.actionToCancel.action;
                                        $scope.listWorkFlowActions[i].fond = data.actionToCancel.fond;
                                        $scope.listWorkFlowActions[i].text = data.actionToCancel.text;
                                        $scope.listWorkFlowActions[i].displaySaveButton = data.actionToCancel.displaySaveButton;
                                        $scope.listWorkFlowActions[i].displayEditButton = data.actionToCancel.displayEditButton;
                                        $scope.listWorkFlowActions[i].displayCancelButton = data.actionToCancel.displayCancelButton;
                                        $scope.listWorkFlowActions[i].enabledButton = true;
                                        $scope.listWorkFlowActions[i].idCallback = data.actionToCancel.idCallback;
                                        $scope.listWorkFlowActions[i].etatSelected = data.actionToCancel.etatSelected;
                                        $scope.listWorkFlowActions[i].etatSuivant = data.actionToCancel.etatSuivant;
                                        $scope.listWorkFlowActions[i].idWorkFlow = data.actionToCancel.idWorkFlow;
                                        $scope.listWorkFlowActions[i].indicateurCommentaire = data.actionToCancel.indicateurCommentaire;
                                        $scope.listEvenementSelected = data.actionToCancel.listEvenementProgramme;
                                        $scope.listProfileSelected = data.actionToCancel.listProfileAutorise;
                                        $scope.listExecutorSelected = data.actionToCancel.listExecutorSelected;
                                        $scope.disabledClassifDiv();
                                    },
                                    function () {}
                            );

                } else {
                    $scope.listWorkFlowActions[i].id = null;
                    $scope.listWorkFlowActions[i].action = null;
                    $scope.listWorkFlowActions[i].etatSuivant = null;
                    $scope.listEvenementSelected = [];
                    $scope.listProfileSelected = [];
                    $scope.listExecutorSelected = [];
                    $scope.listWorkFlowActions.splice(i, 1);
                    $scope.disabledClassifDiv();
                }
            }
        };

        $scope.removeAction = function (action) {
            var i = $scope.listWorkFlowActions.indexOf(action);
            if (i != -1) {
                $scope.listEvenementSelected = [];
                $scope.listProfileSelected = [];
                $scope.listExecutorSelected = [];
                if ($scope.listWorkFlowActions[i].id != null) {
                    $scope.deleteAction($scope.listWorkFlowActions[i].id);
                } else {
                    $scope.listWorkFlowActions.splice(i, 1);
                    $scope.displayAddActionButton = true;
                }
            }
        };

        $scope.deleteAction = function (idAction) {
            if ($scope.selectedProgramme !== null) {
                if ($scope.modeActif == true && $scope.admin == true) {
                    showConfirmation("supprimer", function () {
                        GenericService.get(deleteActionUrl + "/" + idAction)
                                .then(
                                        function (data) {
                                            $scope.loadPaginateActionPermise($scope.selectedEtat.id);
                                        },
                                        function () {}
                                );
                    }, (" Le workflow lié à cette action est actif "));
                } else {
                    showConfirmation("supprimer", function () {
                        jslog("*-*---* " + idAction);
                        GenericService.get(deleteActionUrl + "/" + idAction)
                                .then(
                                        function (data) {
                                            $scope.loadPaginateActionPermise($scope.selectedEtat.id);
                                        },
                                        function () {}
                                );
                    });
                }
            }

        };

        $scope.editAction = function (action) {
            var i = $scope.listWorkFlowActions.indexOf(action);
            if (i != -1) {
                $scope.loadOtherValue(action);
                $scope.listWorkFlowActions[i].enabledButton = false;
                $scope.displayAddActionButton = false;
            }
            $scope.enabledClassifDiv();
        };

        $scope.displayDeleteButtonFunction = function (action) {
//            return ((action.enabledButton == true || action.id == null) && $scope.modeActif == false || ($scope.modeActif == true && $scope.admin == true));
            return ((action.enabledButton == true && action.id != null) && $scope.modeActif == false || ($scope.modeActif == true && $scope.admin == true));

        };


        $scope.paginate = function (selectedPage, page) {
            GenericService.get(listActionPermiseUrl + "/" + selectedPage + "/" + page + "/" + $scope.selectedEtat.id)
                    .then(
                            function (data) {
                                $scope.listWorkFlowActions = data.listActionPermise.content;
                                $scope.actionPage = data.listActionPermise;
                                $scope.pageSizes = data.pageSizes;
                                $scope.sequence = data.sequence;
                                $scope.pageSizeSelect = $scope.pageSizes[0];
                                $scope.modeActif = data.modeActif;
                                $scope.admin = data.adminwkf;
                            },
                            function () {
                            }
                    );
        };

        $scope.loadPaginateActionPermise = function (selectedEtat) {
            GenericService.get(listActionPermiseUrl + "/" + initialNumerOfElements + "/" + firstPage + "/" + selectedEtat)
                    .then(
                            function (data) {
                                $scope.listWorkFlowActions = data.listActionPermise.content;


                                $scope.actionPage = data.listActionPermise;
                                $scope.pageSizes = data.pageSizes;
                                $scope.pageSizeSelect = $scope.pageSizes[0];
                                $scope.sequence = data.sequence;
                                $scope.modeActif = data.modeActif;
                                $scope.admin = data.adminwkf;
                                jslog("*-*-* " + data.adminwkf)
                            },
                            function (errResponse) {
                            }
                    );
        };

        $scope.displayAddActionButtonFunction = function () {
            return $scope.displayAddActionButton && $scope.listeWorkflowCycles[$scope.page.index] && $scope.listeWorkflowCycles[$scope.page.index].typeWorkflow.code !== $scope.WS.code;
        };

        $scope.displayEditActionButtonFunction = function (action) {
            return action.enabledButton && $scope.modeActif === true && $scope.listeWorkflowCycles[$scope.page.index].typeWorkflow.code !== $scope.WS.code;
//            return action.enabledButton && $scope.modeActif === true && $scope.listeWorkflowCycles[$scope.page.index].typeWorkflow.code !== $scope.WS.code;
        };
        $scope.displayEditActionButtonFunctionWs = function (action) {
//            return action.enabledButton && $scope.modeActif === true && $scope.listeWorkflowCycles[$scope.page.index].typeWorkflow.code === $scope.WS.code;
            return action.enabledButton && $scope.modeActif === true && $scope.listeWorkflowCycles[$scope.page.index].typeWorkflow.code === $scope.WS.code;
        };
        $scope.disableOptions = function (action) {

            if ($scope.listeWorkflowCycles[$scope.page.index].typeWorkflow.code !== $scope.WS.code) {
                return action.enabledButton;
            } else {
                return true;
            }
        };

        $scope.map = function () {
            var url = (appUrl + "parametre/conf/workflowsequence/cycle?ide=" + $scope.listeWorkflowCycles[$scope.page.index].id);
            $('#grapgh-view').attr("src", url);
        };

        $scope.displaySelection = function (action) {
            return action.id !== null && $scope.displayAddActionButton;
        };



        $scope.getTemplateParam = function (page) {
            if (page.newe && page.autre === $scope.selectedEvent.id) {
                $scope.params = page;
                return 'edit-param';
            }

            if ($scope.idParam && $scope.idParam && (page.id === $scope.idParam || page.newe) && page.autre === $scope.selectedEvent.id)
                return 'edit-param';
            else
            if ($scope.selectedEvent && page.autre === $scope.selectedEvent.id) {
                return 'display-param';
            }
        };

        $scope.initNewParam = function () {
            if ($scope.listEventParam.length <= 0 || $scope.listEventParam[0].id !== '') {
                $scope.listEventParam.splice(0, 0, {id: '', libelle: '', newe: 1, autre: $scope.selectedEvent.id});
            }

        };

        $scope.saveParam = function (index) {


            if (typeof $scope.params.id !== 'undefined' && typeof $scope.params.libelle !== 'undefined') {


                if (($scope.params.id !== '' && $scope.params.libelle !== '')) {


                    if ($scope.params.newe) {
                        delete  $scope.listEventParam[index].newe;
                    } else {
                        $scope.listEventParam[index] = $scope.params;
                    }
                    delete $scope.idParam;
                    $scope.params = {};
                }
            }
        };

        $scope.resetParam = function () {
            if ($scope.params.newe) {
                $scope.listEventParam.splice(0, 1);
            }
            $scope.params = {};
        };
        $scope.initEventParam = function (e) {
            $scope.paramEdit = 1;
            $scope.selectedEvent = angular.fromJson(e);


        };
        $scope.editParam = function (e) {
            $scope.params = angular.copy(e);
            $scope.idParam = $scope.params.id;
        };

        $scope.deleteParam = function (index) {
            showConfirmation("supprimer", function () {

                $scope.listEventParam.splice(index, 1);
                $scope.$apply();

            }, (" Cette action entraînera la suppression  des actions. "));
        };

        $scope.disabledClassifDiv = function () {
            $("#classifdiv").addClass("disabledbutton");
            $("#executordiv").addClass("disabledbutton");
        };
        $scope.enabledClassifDiv = function () {
            $("#classifdiv").removeClass("disabledbutton");
            $("#executordiv").removeClass("disabledbutton");
        };

        $scope.disabledClassifDiv();

        $scope.getNumero = function (num) {
            var n = "" + num + "";

            while (n.length < 7) {
                n = "0" + n;
            }
            return n;
        };



        $scope.canEditElement = function () {
            if ($scope.modele.statutDto.ideCode == 1) {
                return false;
            }
            if ($scope.modele.statutDto.ideCode == 2) {
                return true;
            }
            return false;
        };

        $scope.verifierStatut = function () {
            $scope.statut = $scope.modele.statutDto.ideCode;
            jslog('DDDDDDDDD ' + angular.toJson($scope.statut));
            if ($scope.statut == 1) {
                return true;
            }
            if ($scope.statut == 2) {
                return true;
            }
            if ($scope.statut == 3) {
                return false;
            }
        };
    }]);
