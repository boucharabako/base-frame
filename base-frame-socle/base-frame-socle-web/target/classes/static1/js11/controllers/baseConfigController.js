/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

var App;
var appUrl;
App.controller('baseConfigController', ['$rootScope', '$scope', '$timeout', 'GenericService', 'PropagationService', function ($rootScope, $scope, $timeout, GenericService, PropagationService) {
        var baseUrl = appUrl + 'api/parametre/conf/classeur/';
        var baseUrl2 = appUrl + 'api/configuration/v2/';
        var findCodification = baseUrl + 'find-codification-classeurs';
        var saveCodificaion = baseUrl + 'save-codification';
        var saveClasseur = baseUrl + 'save-classeur';
        var deleteClasseur = baseUrl + 'delete-classeur';
        var deleteCodification = baseUrl + 'delete-codif';
        var statutEnSaisieUrl = baseUrl + 'find-statut-en-saissie';
        var typeOperation = $('#classeur-type-operation').val();
        var domaineID = $('#classeur-domaine-id').val();
        var porteeID = $('#classeur-domaine-id').val();
        var typeOperation = $('#classeur-portee-id').val();

        var PERSIST_TYPE_UPDATE = "up";
        var PERSIST_TYPE_INSERT = "in";

        var NATURE_ADD_ROOT = "root";
        var NATURE_ADD_CHILD = "child";
        var NATURE_UPDATE = "update";


        $scope.domaines = [];
        $scope.classeurs = [];
        $scope.portees = [];
        $scope.statutDto = null;

        $scope.ignoreChanges = false;
        $scope.ignoreChanges2 = false;
        $scope.node = null;

        $scope.selectedClasseur = {code: null, libelle: null, parent: null, getParent: function () {

                return $scope.treeJsOp.getOne(parent);

            }};

        $scope.codificationEdited = {editable: true, code: null, libelle: null, porte: $('#classeur-portee-id').val() ? $('#classeur-portee-id').val() : null,
            domaine: $('#classeur-domaine-id').val() ? $('#classeur-domaine-id').val() : null, statutDto: null};
        $scope.codificationModel = {editable: true, code: null, libelle: null, porte: $('#classeur-portee-id').val() ? $('#classeur-portee-id').val() : null,
            domaine: $('#classeur-domaine-id').val() ? $('#classeur-domaine-id').val() : null, statutDto: null};

        console.log($scope.codificationModel);
        $scope.getDomaines = function () {
            GenericService.get(appUrl + "api/utils/codification/DOMAINES")
                    .then(
                            function (data) {
                                $scope.domaines = data;
                            },
                            function (errResponse) {
                            }
                    );
        };
        $scope.getDomaines();
        $scope.getPortees = function () {
            GenericService.get(appUrl + "api/utils/codification/PORTEE")
                    .then(
                            function (data) {
                                $scope.portees = data;
                            },
                            function (errResponse) {
                            }
                    );
        };
        $scope.getPortees();
        $scope.getStatutEnSaisie = function () {
            GenericService.get(statutEnSaisieUrl)
                    .then(
                            function (data) {
//                                jslog("cycleVieController getUsers  : " + angular.toJson(data));
                                $scope.statutDto = data;
                                $scope.codificationEdited.statutDto = data;
                                $scope.codificationModel.statutDto = data;
                            },
                            function (errResponse) {
                            }
                    );
        };
        $scope.getStatutEnSaisie();
        $scope.getCodification = function () {
            var codificationId = $('#classeur-code').val();
            if (codificationId) {
                GenericService.get(findCodification + "?code=" + codificationId)
                        .then(
                                function (data) {
                                    $scope.statutDto = data;
                                    $scope.classeurs = data.classeurs;
                                    $scope.codificationEdited.code = data.code;
                                    $scope.codificationEdited.libelle = data.libelle;
                                    $scope.codificationEdited.porte = data.porte;
                                    $scope.codificationEdited.domaine = data.domaine;
                                    $scope.codificationEdited.editable = data.editable;
                                    $scope.codificationEdited.statutDto = data.statutDto;
                                    if ($('#classeur-detailAtr').val()) {
                                        $scope.codificationEdited.editable = false;
                                    }
                                    $scope.treeJsOp.addAll(data.classeurs);
                                    $scope.ignoreChanges = true;
                                    $scope.ignoreChanges2 = true;
                                },
                                function (errResponse) {
                                }
                        );

            }

        };
        $scope.loadClasseurItem = function () {
            if ($scope.fullObject.code != null && $scope.fullObject.type != null && $scope.fullObject.type.ideCode != null) {
                GenericService.get(getItemsUrl + "/" + $scope.fullObject.code + "?t=" + $scope.fullObject.type.ideCode)
                        .then(
                                function (data) {


                                    $scope.treeJsOp.addAll(data);
                                    $scope.ignoreChanges = true;
                                    $scope.ignoreChanges2 = true;


                                },
                                function () {}
                        );
            }
        };


        $scope.getCodification();
        $scope.saveCodofication = function () {
            GenericService.post(saveCodificaion + "?t=" + typeOperation, $scope.codificationEdited)
                    .then(
                            function (data) {
                                if (typeOperation === PERSIST_TYPE_INSERT) {
                                    typeOperation = PERSIST_TYPE_UPDATE;
                                }
                                $scope.ignoreChanges = true;
                                $scope.ignoreChanges2 = true;
                            },
                            function (errResponse) {
                            }
                    );



        };
        $scope.saveClasseur = function (nature) {
            if (nature === NATURE_ADD_CHILD && $scope.node !== null) {
                $scope.selectedClasseur.parent = $scope.node.id;
            }
            $scope.selectedClasseur.codification = $scope.fullObject.code;
            GenericService.post(saveClasseur, $scope.selectedClasseur)
                    .then(
                            function (data) {
                                $scope.selectedClasseur.id = data.id;
                                if (nature === NATURE_UPDATE) {
                                    $scope.treeJsOp.update($scope.selectedClasseur);
                                } else {
                                    $scope.treeJsOp.add($scope.selectedClasseur);
                                }
                                $scope.selectedClasseur = {code: null, id: null, libelle: null, parent: null};
                            },
                            function (errResponse) {
                            }
                    );



        };
        $scope.deleteClasseur = function () {
            showConfirmation("supprimer", function () {
                GenericService.post(deleteClasseur, {id: $scope.node.id})
                        .then(
                                function (data) {
                                    $scope.selectedClasseur = {code: null, id: null, libelle: null, parent: null};
                                    $scope.treeJsOp.data = [];
                                    $scope.treeJsOp.addAll(data.classeurs);
                                    $scope.node = null;
                                },
                                function (errResponse) {
                                }
                        );

            }, (" Cette action entraînera la suppression  de toutes les feuilles. "));

        };

        $scope.resetCodif = function () {
            $scope.treeJsOp.data = [];

            $scope.selectedClasseur = {code: null, id: null, libelle: null, parent: null};
            $scope.node = null;
            $scope.treeConfig.version++;
            angular.copy($scope.codificationModel, $scope.codificationEdited);
            typeOperation = PERSIST_TYPE_INSERT;
            $scope.ignoreChanges2 = false;
        };
        $scope.deleteCodification = function () {
            showConfirmation("supprimer", function () {
                console.log({codification: $scope.codificationEdited.code});
                GenericService.post(deleteCodification, {codification: $scope.codificationEdited.code})
                        .then(
                                function (data) {
                                    $scope.treeJsOp.data = [];

                                    $scope.selectedClasseur = {code: null, id: null, libelle: null, parent: null};
                                    $scope.node = null;
                                    $scope.treeConfig.version++;
                                    angular.copy($scope.codificationModel, $scope.codificationEdited);
                                    typeOperation = PERSIST_TYPE_INSERT;
                                    $scope.resetCodif();


                                },
                                function (errResponse) {
                                }
                        );
            }, (" Cette action entraînera la suppression  de toutes les feuilles. "));


        };
        $scope.init = function () {

            $scope.treeJsOp.data = [];

            $scope.selectedClasseur = {code: null, id: null, libelle: null, parent: null};
            angular.copy($scope.codificationModel, $scope.codificationEdited);
        };

        $scope.treeJsOp = {
            data: [],
            data2: [],
            add: function (data) {
                if ($scope.node && $scope.node !== null) {
                    var p = $scope.node.id;
                    for (k = 0; k < $scope.treeJsOp.data.length; k++) {

                        if ($scope.node.id === $scope.treeJsOp.data[k].id) {
                            $scope.treeJsOp.data[k].state = {opened: true, selected: true};

                        } else {
                            $scope.treeJsOp.data[k].state = {selected: false};
                        }

                    }

                }
                this.data.push({id: data.id, text: data.libelle, parent: data.parent !== null ? data.parent : '#', code: data.code});


                $scope.ignoreChanges = true;
                $scope.treeConfig.version++;
            },
            update: function (data) {
                if ($scope.node && $scope.node !== null) {
                    var p = $scope.node.id;
                    for (k = 0; k < $scope.treeJsOp.data.length; k++) {

                        if ($scope.node.id === $scope.treeJsOp.data[k].id) {
                            $scope.treeJsOp.data[k].state = {opened: true, selected: true};
                            $scope.treeJsOp.data[k].text = data.libelle;
                            $scope.treeJsOp.data[k].code = data.code;
                            $scope.node.text = data.libelle;
                            $scope.node.code = data.code;
                            $scope.node.edited = false;
                            ;

                        } else {
                            $scope.treeJsOp.data[k].state = {selected: false};
                        }

                    }

                }


                $scope.ignoreChanges = true;
                $scope.treeConfig.version++;
            },
            addAll: function (classeurs) {
                angular.forEach(classeurs, function (val, key) {
                    $scope.treeJsOp.data.push({id: val.id, text: val.libelle, code: val.code,
                        parent: val.parent !== null ? val.parent : '#', state: {opened: false}});


                });


                if ($scope.node !== null && $scope.node.parent !== null && $scope.node.parent !== "#") {
                    var pr = $scope.node.parent;
                    for (k = 0; k < $scope.treeJsOp.data.length; k++) {
                        if (pr === $scope.treeJsOp.data[k].id) {
                            console.log($scope.treeJsOp.data[k]);
                            $scope.treeJsOp.data[k].state = {opened: true, selected: false};
                        } else {
                            $scope.treeJsOp.data[k].state = {selected: false};
                        }

                    }
                    $scope.node = null;
                }
                $scope.treeConfig.version++;

                console.log($scope.treeJsOp.data);
            },
            remoove: function (id) {
                var item;
                angular.forEach(this.data, function (val, key) {
                    if (val.id === id) {
                        item = val;
                    }
                });
                this.data.splice(this.data.indexOf(item), 1);
                $scope.ignoreChanges = true;
                $scope.treeConfig.version++;
            },
            getOne: function (id) {
                if (!id || id === null) {
                    return "";
                }
                var item;
                angular.forEach(this.data, function (val, key) {
                    if (val.id === id) {
                        item = val;
                    }
                });

                return item ? item.text : "";
            }
        };

        $scope.treeConfig = {
            core: {
                "multiple": false,
                "animation": 0,
                "check_callback": true,
                "themes": {"stripes": true}
            },
            "types": {
                "#": {
                    "max_children": 100,
                    "max_depth": 100,
                    "valid_children": ["default"]
                },
                "root": {
                    'icon': 'fa fa-folder',
                    "valid_children": ["default"]
                },
                'default': {
                    'icon': 'fa fa-folder'
                },
                'file': {
                    'icon': 'fa fa-file'
                }
            }, "html_titles": true, "load_open": true,
            "plugins": [
                "search", "types", "checkbox"
            ],
            'checkbox': {
                'deselect_all': true,
                'three_state': false
            },
            version: 1
        };

        angular.copy($scope.originalData, $scope.treeJsOp.data);
        angular.copy($scope.treeJsOp.data2, $scope.treeJsOp.data);

        $scope.applyModelChanges = function () {
            return !$scope.ignoreChanges;
        };

        $scope.selectNode = function (event, data) {

            $scope.node = data.node;
            $scope.node.code = $scope.node.original.code;
            $scope.node.edited = false;
        };
        $scope.deselectNode = function (event, data) {
            $scope.node = null;
        };
        $scope.openNode = function (event, data) {
        };

        $scope.editNode = function () {
            if ($scope.node != null) {
                $scope.selectedClasseur = {id: $scope.node.id, code: $scope.node.code, libelle: $scope.node.text, parent: $scope.node.parent};
                $scope.node.edited = true;
            }
        };
//=======================================================================================================================

        $scope.dynamicObject = {typeCodif: null, domaineOrPorte: null, typeCodifKey: null, domaineKey: null, porteKey: null};
        $scope.dynamicObjectMaster = {typeCodif: null, domaineOrPorte: null, typeCodifKey: null, domaineKey: null, porteKey: null};

        $scope.statutDto = {id: null, libelle: "En saisie", ideCode: "1", libelleStatutSuivant: null, ideCodeStatutSuivant: null, nextStatutBackColor: null, nextStatutTextColor: null, statutTextColor: "#FFFFFF", statutBackColor: "#757575", editable: null, nextStatutIdeCode: null};
        $scope.mode = null;

        $scope.fullObject = {
            code: null, libelle: null,
            porte: {ideCode: $scope.dynamicObject.porteKey, libelle: null},
            domaine: {ideCode: $scope.dynamicObject.domaineKey, libelle: null},
            type: {ideCode: null, libelle: null}, statutDto: $scope.statutDto};

        $scope.fullObjectMaster = {
            code: null, libelle: null,
            porte: {ideCode: $scope.dynamicObject.porteKey, libelle: null},
            domaine: {ideCode: $scope.dynamicObject.domaineKey, libelle: null},
            type: {ideCode: null, libelle: null}, statutDto: $scope.statutDto};

        const listUrl = baseUrl2 + 'liste';
        const changeStatutUrl = baseUrl2 + 'changer-statut';
        const saveCodificationURL = baseUrl2 + 'save';
        const deleteUrl = baseUrl2 + 'delete';
        const loadEtatInitialUrl = baseUrl2 + "etatInitialProvider";
        const loadDomaineUrl = baseUrl2 + "etatInitialProvider";
        const loadPorteUrl = baseUrl2 + "etatInitialProvider";
        const removeItemUrl = baseUrl2 + "deleteItem";
        const saveItemUrl = baseUrl2 + "save-liste";
        const saveParamItemUrl = baseUrl2 + "save-param";
        const saveEtiquetteItemUrl = baseUrl2 + "save-etiquette";
        const getItemsUrl = baseUrl2 + "getitem";

        $scope.listData = [];
        $scope.sequenceCodif = [];
        $scope.listDataPage = {number: ""};

        var firstPage = 1;
        var initialNumerOfElements = 5;

        $scope.searchObject = {typeCodif: '', porte: '', domaine: '', motCle: ''};
        $scope.searchObjectMaster = {typeCodif: '', porte: '', domaine: '', motCle: ''};
        $scope.motCle = null;
        $scope.conceptMetier = $("#conceptMetier").val();

        $scope.loadConfigurationTree = function () {
            $('#configTree').jstree({
                checkbox: {
                    three_state: false
                },
                'plugins': ['search', 'types', 'checkbox'],
                "core": {
                    "multiple": false,
                    "animation": 0,
                    "check_callback": true,
                    "themes": {"stripes": true},
                    'data': {
                        'dataType': "json",
                        'url': function (node) {
                            var url = $('#marbre_noeud').attr('href');
                            return url + "api/configuration/v2/arbre/" + $scope.type;
                        },
                        'data': function (node) {
                            return {'n': node.id};
                        }
                    }
                },
                "types": {
                    "#": {
                        "max_children": 3,
                        "max_depth": 4,
                        "valid_children": ["default"]
                    },
                    "root": {
                        'icon': 'fa fa-folder',
                        "valid_children": ["default"]
                    },
                    'default': {
                        'icon': 'fa fa-folder'
                    },
                    'file': {
                        'icon': 'fa fa-file'
                    }
                }, "html_titles": true, "load_open": true
            });
            $('#configTree').jstree("refresh");
        };

        $("#configTree").on("select_node.jstree", function (evt, data) {
            if (data.node.id !== null) {
                $scope.dynamicObject = angular.copy($scope.dynamicObjectMaster);
                $scope.dynamicObject.typeCodif = data.node.original.libelleParent;
                $scope.dynamicObject.domaineOrPorte = data.node.text;
                $scope.dynamicObject.typeCodifKey = data.node.parent;
                $scope.fullObject.type.ideCode = $scope.dynamicObject.typeCodifKey;

                if ($scope.type == 'DOMAINES') {
                    $scope.dynamicObject.domaineKey = data.node.original.code;
                    $scope.dynamicObject.porteKey = '';
                    $scope.fullObject.domaine.ideCode = $scope.dynamicObject.domaineKey;
                    $scope.fullObject.porte.ideCode = null;

                }
                if ($scope.type == 'PORTEE') {
                    $scope.dynamicObject.domaineKey = '';
                    $scope.dynamicObject.porteKey = data.node.original.code;
                    $scope.fullObject.porte.ideCode = $scope.dynamicObject.porteKey;
                    $scope.fullObject.domaine.ideCode = null;
                }
                $scope.loadListFunction();
//                jslog("TEST "+$scope.dynamicObject.domaineOrPorte);
            }
            $scope.$apply();
        });
        $("#configTree").on("deselect_node.jstree", function (evt, data) {
            $scope.dynamicObject = angular.copy($scope.dynamicObjectMaster);
            $scope.fullObject = angular.copy($scope.fullObjectMaster);
            $scope.listData = [];
            $scope.$apply();
        });

        $scope.switchTab = function (arg) {
            $scope.dynamicObject = angular.copy($scope.dynamicObjectMaster);
            $scope.searchObject = angular.copy($scope.searchObjectMaster);
            if (arg == 'D') {
                $scope.type = 'DOMAINES';
            }
            if (arg == 'P') {
                $scope.type = 'PORTEE';
            }
            $scope.listData = [];
            if ($scope.type && $scope.type != null) {
                $scope.loadConfigurationTree();
            }
        };

        $scope.switchTab('D');

        $scope.loadListFunction = function () {
            if ($scope.motCle != null && typeof ($scope.motCle) != 'undefined') {
                $scope.searchObject.motCle = $scope.motCle;
            }
            if ($scope.dynamicObject.typeCodifKey && $scope.dynamicObject.typeCodifKey != null) {
                $scope.searchObject.typeCodif = $scope.dynamicObject.typeCodifKey;
            }
            if ($scope.type == 'DOMAINES') {
                $scope.searchObject.domaine = $scope.dynamicObject.domaineKey;
                $scope.searchObject.porte = '';
            }
            if ($scope.type == 'PORTEE') {
                $scope.searchObject.domaine = '';
                $scope.searchObject.porte = $scope.dynamicObject.porteKey;
            }

            var URL = listUrl +
                    "?t=" + $scope.searchObject.typeCodif +
                    ($scope.searchObject.porte == '' ? "&d=" + $scope.searchObject.domaine : "&p=" + $scope.searchObject.porte) +
                    "&m=" + $scope.searchObject.motCle +
                    "&page=" + firstPage +
                    "&size=" + initialNumerOfElements;
            GenericService.get(URL)
                    .then(
                            function (data) {
                                $scope.listData = data.object.content;
                                $scope.sequenceCodif = data.sequence;
                                $scope.listDataPage = data.listDataPage;
                            },
                            function () {}
                    );
        };

        $scope.rechercher = function () {
            $scope.loadListFunction();
        };

        $scope.printDomaineOrPorte = function (domaine, porte) {
            if ($scope.type == 'DOMAINES') {
                return domaine;
            }
            if ($scope.type == 'PORTEE') {
                return porte;
            }
        };

        $scope.deleteCodificationFunction = function (l) {
//            jslog("lObject "+angular.toJson(l));
            var i = $scope.listData.indexOf(l);
            showConfirmation("supprimer ", function () {
                GenericService.post(deleteUrl, angular.toJson(l))
                        .then(
                                function (data) {
                                    if (data.msg && data.msg.typeError && data.msg.typeError == 'INFO') {
                                        $scope.listData.splice(i, 1);
                                    }
                                },
                                function () {}
                        );
            });
        };

        $scope.changeStatut = function (codeObject, statutDto, index) {
            statutDto.id = codeObject;
            showConfirmation(statutDto.libelleStatutSuivant, function () {
                GenericService.post(changeStatutUrl, angular.toJson(statutDto))
                        .then(
                                function (data) {
                                    if (data.msg && data.msg.typeError && data.msg.typeError == 'INFO') {
                                        if (index == null) {
                                            $scope.fullObject.statutDto = angular.copy(data.value);
                                        } else {
                                            $scope.listData[index].statutDto = angular.copy(data.value);
                                        }
                                    }
                                },
                                function () {}
                        );
            });
        };


        $scope.displayActionButton = function (statutDto) {
            return statutDto.ideCode != "3";
        };

        $scope.displayDeleteButton = function (statutDto) {
            if (statutDto.ideCode == "3" || statutDto.ideCode == "2") {
                return false;
            }
            return true;
        };
        $scope.displayEditButton = function (statutDto, l) {
            if (statutDto.ideCode == "3" || statutDto.ideCode == "2") {
                return false;
            } else {
                return true;
            }
        };

        $scope.paginateCodification = function (page) {

            if ($scope.motCle != null && typeof ($scope.motCle) != 'undefined') {
                $scope.searchObject.motCle = $scope.motCle;
            }
            if ($scope.dynamicObject.typeCodifKey && $scope.dynamicObject.typeCodifKey != null) {
                $scope.searchObject.typeCodif = $scope.dynamicObject.typeCodifKey;
            }
            if ($scope.type == 'DOMAINES') {
                $scope.searchObject.domaine = $scope.dynamicObject.domaineKey;
                $scope.searchObject.porte = '';
            }
            if ($scope.type == 'PORTEE') {
                $scope.searchObject.domaine = '';
                $scope.searchObject.porte = $scope.dynamicObject.porteKey;
            }

            var URL = listUrl +
                    "?t=" + $scope.searchObject.typeCodif +
                    ($scope.searchObject.porte == '' ? "&d=" + $scope.searchObject.domaine : "&p=" + $scope.searchObject.porte) +
                    "&m=" + $scope.searchObject.motCle +
                    "&page=" + page +
                    "&size=" + initialNumerOfElements;
            GenericService.get(URL)
                    .then(
                            function (data) {
                                $scope.listData = data.object.content;
                                $scope.sequenceCodif = data.sequence;
                                $scope.listDataPage = data.listDataPage;
                            },
                            function () {}
                    );
        };

        $scope.loadEtatInitial = function () {
            GenericService.get(loadEtatInitialUrl)
                    .then(
                            function (data) {

                                $scope.finalObject.etat = data.etatObject;
                            },
                            function () {
                            }
                    );
        };

        $scope.openRightModal = function () {
            $scope.mode = "IN";
            switch ($scope.dynamicObject.typeCodifKey) {
                case "L":
                    $('#detailListeBase').modal('show');
                    $scope.listItem = [];
                    break;
                case "P":
                    $('#detailParametreBase').modal('show');
                    $scope.listParamItem = [];
                    break;
                case "E":
                    $('#detailEtiquetteBase').modal('show');
                    $scope.listEtiquetteItem = [];
                    break;
                case "C":
                    $('#detailClassificationBase').modal('show');
                    $scope.init();
                    $scope.resetCodif();
                    break;
                default:
                    break;
            }
            $scope.fullObject.statutDto = angular.copy($scope.statutDto);
        };

        $('#detailListeBase').on('hidden.bs.modal', function () {
            $scope.mode = "IN";
            $scope.fullObject.code = null;
            $scope.fullObject.libelle = null;
            $scope.initButton();
        });
        $('#detailParametreBase').on('hidden.bs.modal', function () {
            $scope.mode = "IN";
            $scope.fullObject.code = null;
            $scope.fullObject.libelle = null;
            $scope.initButton();

        });
        $('#detailEtiquetteBase').on('hidden.bs.modal', function () {
            $scope.mode = "IN";
            $scope.fullObject.code = null;
            $scope.fullObject.libelle = null;
            $scope.initButton();
        });
        $('#detailClassificationBase').on('hidden.bs.modal', function () {
            $scope.mode = "IN";
            $scope.fullObject.code = null;
            $scope.fullObject.libelle = null;
            $scope.initButton();
            $scope.$apply();
            $scope.init();
            $scope.resetCodif();
        });

        /**
         * MODAL OPENE HANDLER
         * @returns {Boolean}
         */

        $('#detailListeBase').on('show.bs.modal', function () {
        });
        $('#detailParametreBase').on('show.bs.modal', function () {
        });
        $('#detailEtiquetteBase').on('show.bs.modal', function () {
        });
        $('#detailClassificationBase').on('show.bs.modal', function () {
        });

        $scope.initButton = function () {
            if ($scope.type == 'DOMAINES') {
                $scope.fullObject.porte.ideCode = null;
                $scope.$apply();
            }
        };

        $scope.displayAddButton = function () {
            return $scope.dynamicObject.typeCodifKey != null && $scope.fullObject.porte.ideCode != "S";
        };

        $scope.disabledCodeFunction = function () {
            return $scope.mode == 'DE' || $scope.mode == 'UP';
        };

        $scope.saveOneCodification = function () {
            var operationUrl = saveCodificationURL + "?t=" + ($scope.mode == 'IN' ? 'in' : 'up');
            GenericService.post(operationUrl, angular.toJson($scope.fullObject))
                    .then(
                            function (data) {
                                if (data.msg && data.msg.typeError && data.msg.typeError == 'INFO') {
                                    $scope.mode = "UP";
                                    $scope.loadListFunction();
                                    $scope.ignoreChanges = true;
                                    $scope.ignoreChanges2 = true;
                                }
                            },
                            function () {}
                    );
        };

        $scope.cancelOneCodification = function () {

        };

        $scope.exitOneCodification = function () {

        };

        $scope.detailCodificationFunction = function (l) {
            $scope.mode = 'DE';
            $scope.fullObject.code = l.code;
            $scope.fullObject.libelle = l.libelle;
            $scope.fullObject.type.ideCode = l.type.ideCode;
            $scope.fullObject.porte.ideCode = l.porte.ideCode;
            $scope.fullObject.domaine.ideCode = l.domaine.ideCode;
            $scope.fullObject.statutDto = l.statutDto;
            $scope.displayAddItemButton = true;
            switch ($scope.dynamicObject.typeCodifKey) {
                case "L":
                    $('#detailListeBase').modal('show');
                    $scope.loadItems();
                    break;
                case "P":
                    $('#detailParametreBase').modal('show');
                    $scope.loadParamItems();
                    break;
                case "E":
                    $('#detailEtiquetteBase').modal('show');
                    $scope.loadEtiquetteItems();
                    break;
                case "C":
                    $('#detailClassificationBase').modal('show');
                    $scope.loadClasseurItem();
                    break;
                default:
                    break;
            }
        };

        $scope.editCodificationFunction = function (l) {
            $scope.mode = 'UP';
            $scope.fullObject.code = l.code;
            $scope.fullObject.libelle = l.libelle;
            $scope.fullObject.type.ideCode = l.type.ideCode;
            $scope.fullObject.porte.ideCode = l.porte.ideCode;
            $scope.fullObject.domaine.ideCode = l.domaine.ideCode;
            $scope.fullObject.statutDto = l.statutDto;
            $scope.displayAddItemButton = true;
            switch ($scope.dynamicObject.typeCodifKey) {
                case "L":
                    $('#detailListeBase').modal('show');
                    $scope.loadItems();
                    break;
                case "P":
                    $('#detailParametreBase').modal('show');
                    $scope.loadParamItems();
                    break;
                case "E":
                    $('#detailEtiquetteBase').modal('show');
                    $scope.loadEtiquetteItems();
                    break;
                case "C":
                    $('#detailClassificationBase').modal('show');
                    $scope.loadClasseurItem();
                    break;
                default:
                    break;
            }


        };

        $scope.disableDomaineFunction = function () {
            if ($scope.mode == "UP" || $scope.mode == "DE") {
                return true;
            }

            if ($scope.mode == "IN" && $scope.type == "DOMAINES") {
                return true;
            } else {
                return false;
            }
        };
        $scope.disablePorteFunction = function () {
            if ($scope.mode == "UP" || $scope.mode == "DE") {
                return true;
            }

            if ($scope.mode == "IN" && $scope.type == "PORTEE") {
                return true;
            } else {
                return false;
            }
        };

        $scope.disabledCodificationLibelleFunction = function () {
            return $scope.mode == 'DE';
        };

        $scope.displaySaveButtonFunction = function () {
            return $scope.mode != 'DE';
        };
        $scope.displayCancelButtonFunction = function () {
            return $scope.mode != 'DE';
        };

        $scope.displayAddItemButton = true;
        $scope.displayItemAddButton = function () {
            return $scope.displayAddItemButton && $scope.mode == 'UP' && $scope.fullObject.statutDto.ideCode == "1";
        };

        $scope.displaySaveItemButton = true;
        $scope.displayCancelItemButton = $scope.displaySaveItemButton;
        $scope.displayEditItemButton = !$scope.displaySaveItemButton;
        $scope.displayRemoveItemButton = !$scope.displaySaveItemButton;

        $scope.displaySaveItemButtonFunction = function (item, $index) {
            return item && !item.disabled;
        };
        $scope.displayCancelItemButtonFunction = function (item, $index) {
            return item && (!item.disabled && item.id == null || (!item.disabled && item.id != null));
        };
        $scope.displayEditItemButtonFunction = function (item, $index) {
            return (item.disabled && item.id != null) && $scope.fullObject.statutDto.ideCode == "1" && $scope.mode == 'UP';
        };
        $scope.displayRemoveItemButtonFunction = function (item, $index) {
            return (item.disabled && item.id != null) && $scope.fullObject.statutDto.ideCode == "1" && $scope.mode == 'UP';
        };

        $scope.disbaledCodeFunction = function (item) {
            return item && item.disabled;
        };
        $scope.disabledLibelleFunction = function (item) {
            return item && item.disabled;
        };

        $scope.listItem = [];
        $scope.itemObjectBackup = {id: null, code: null, libelle: null, codification: null, disabled: false, type: {ideCode: null, libelle: null}, displayGroup: true};
        $scope.addNewItem = function () {
            $scope.itemObject = {id: null, code: null, libelle: null, codification: null, disabled: false, type: {ideCode: null, libelle: null}, displayGroup: true};
            $scope.itemObject.type = angular.copy($scope.fullObject.type);
            $scope.itemObject.codification = $scope.fullObject.code;
            $scope.listItem.splice(0, 0, angular.copy($scope.itemObject));
            $scope.displayAddItemButton = false;
        };

        $scope.saveItem = function (item, index) {
            GenericService.post(saveItemUrl, angular.toJson(item))
                    .then(
                            function (data) {
                                if (data.msg && data.msg.typeError && data.msg.typeError == 'INFO') {
                                    $scope.loadItems();
                                    $scope.displayAddItemButton = true;
                                }
                            },
                            function () {}
                    );
        };

        $scope.cancelItem = function (item, index) {
            if (item.id != null) {
                $scope.listItem[index] = angular.copy($scope.itemObjectBackup);
                $scope.listItem[index].disabled = true;
            } else {
                $scope.listItem.splice(index, 1);
            }
            $scope.displayAddItemButton = true;
        };

        $scope.editItem = function (item, index) {
            $scope.listItem[index].disabled = false;
            $scope.displayAddItemButton = false;
            $scope.itemObjectBackup = angular.copy($scope.listItem[index]);
        };

        $scope.removeItem = function (item, index) {
            if (item.id != null) {
                showConfirmation("supprimer ", function () {
                    GenericService.post(removeItemUrl, item)
                            .then(
                                    function (data) {
                                        $scope.listItem.splice(index, 1);
                                    },
                                    function () {}
                            );
                });
            }
        };

        $scope.loadItems = function () {
            if ($scope.fullObject.code != null && $scope.fullObject.type != null && $scope.fullObject.type.ideCode != null) {
                GenericService.get(getItemsUrl + "/" + $scope.fullObject.code + "?t=" + $scope.fullObject.type.ideCode)
                        .then(
                                function (data) {
                                    $scope.listItem = data;
                                },
                                function () {}
                        );
            }
        };

        $scope.controlTable = function () {
            for (var i = 1; i >= $scope.listItem.length; i++) {
                $scope.listItem[i].displayGroup = false;
            }
        };

//        ===========================================================STARTING PARAM====================================================================================

        $scope.displayAddParamItemButton = true;
        $scope.displayParamItemAddButton = function () {
            return $scope.displayAddParamItemButton && $scope.mode == 'UP';
        };

        $scope.listParamItem = [];
        $scope.paramItemObjectBackup = {id: null, code: null, valeur: null, dateDebutValidite: null, dateFinValidite: null, codification: null, disabled: false, type: {ideCode: null, libelle: null}, displayGroup: true};
        $scope.addNewParamItem = function () {
            $scope.paramItemObject = {id: null, code: null, valeur: null, dateDebutValidite: null, dateFinValidite: null, codification: null, disabled: false, type: {ideCode: null, libelle: null}, displayGroup: true};
            $scope.paramItemObject.type = angular.copy($scope.fullObject.type);
            $scope.paramItemObject.codification = $scope.fullObject.code;
            $scope.listParamItem.splice(0, 0, angular.copy($scope.paramItemObject));
            $scope.displayAddParamItemButton = false;
        };

        $scope.displayEditParamItemButtonFunction = function (item, $index) {
            return (item.disabled && item.id != null) && $scope.fullObject.statutDto.ideCode == "1" && $scope.mode == 'UP';
        };

        $scope.displayRemoveParamItemButtonFunction = function (item, $index) {
            return (item.disabled && item.id != null) && $scope.fullObject.statutDto.ideCode == "1" && $scope.mode == 'UP';
        };

        $scope.disabledValeurFunction = function (item) {
            return (item && item.disabled);
        };

        $scope.saveParamItem = function (item, index) {
            GenericService.post(saveParamItemUrl, angular.toJson(item))
                    .then(
                            function (data) {
                                if (data.msg && data.msg.typeError && data.msg.typeError == 'INFO') {
                                    $scope.loadParamItems();
                                    $scope.displayAddParamItemButton = true;
                                }
                            },
                            function () {}
                    );
        };

        $scope.cancelParamItem = function (item, index) {
            if (item.id != null) {
                $scope.listParamItem[index] = angular.copy($scope.paramItemObjectBackup);
                $scope.listParamItem[index].disabled = true;
            } else {
                $scope.listParamItem.splice(index, 1);
            }
            $scope.displayAddParamItemButton = true;
        };

        $scope.editParamItem = function (item, index) {
            $scope.listParamItem[index].disabled = false;
            $scope.displayAddParamItemButton = false;
            $scope.paramItemObjectBackup = angular.copy($scope.listParamItem[index]);
        };

        $scope.removeParamItem = function (item, index) {
            if (item.id != null) {
                showConfirmation("supprimer ", function () {
                    GenericService.post(removeItemUrl, item)
                            .then(
                                    function (data) {
                                        $scope.listParamItem.splice(index, 1);
                                    },
                                    function () {}
                            );
                });
            }
        };

        $scope.loadParamItems = function () {
            if ($scope.fullObject.code != null && $scope.fullObject.type != null && $scope.fullObject.type.ideCode != null) {
                GenericService.get(getItemsUrl + "/" + $scope.fullObject.code + "?t=" + $scope.fullObject.type.ideCode)
                        .then(
                                function (data) {
                                    $scope.listParamItem = data;
                                    jslog("DATA "+angular.toJson(data))
                                    $scope.listParamItem.forEach(function (p) {
                                        p.dateDebutValidite = new Date(p.dateDebutValidite);
                                        p.dateFinValidite = (p.dateFinValidite == null ? null : new Date(p.dateFinValidite));
                                    });
                                },
                                function () {}
                        );
            }
        };
//        ========================================================STARTING ETIQUETTE================================================================================

        $scope.displayAddEtiquetteItemButton = true;
        $scope.displayEtiquetteItemAddButton = function () {
            return $scope.displayAddEtiquetteItemButton && $scope.mode == 'UP';
        };

        $scope.listEtiquetteItem = [];
        $scope.etiquetteItemObjectBackup = {id: null, code: null, valeur: null, dateDebutValidite: null, dateFinValidite: null, codification: null, disabled: false, type: {ideCode: null, libelle: null}, displayGroup: true};
        $scope.addNewEtiquetteItem = function () {
            $scope.etiquetteItemObject = {id: null, code: null, valeur: null, dateDebutValidite: null, dateFinValidite: null, codification: null, disabled: false, type: {ideCode: null, libelle: null}, displayGroup: true};
            $scope.etiquetteItemObject.type = angular.copy($scope.fullObject.type);
            $scope.etiquetteItemObject.codification = $scope.fullObject.code;
            $scope.listEtiquetteItem.splice(0, 0, angular.copy($scope.etiquetteItemObject));
            $scope.displayAddEtiquetteItemButton = false;
        };

        $scope.displayEditEtiquetteItemButtonFunction = function (item, $index) {
            return (item.disabled && item.id != null) && $scope.fullObject.statutDto.ideCode == "1" && $scope.mode == 'UP';
        };

        $scope.displayRemoveEtiquetteItemButtonFunction = function (item, $index) {
            return (item.disabled && item.id != null) && $scope.fullObject.statutDto.ideCode == "1" && $scope.mode == 'UP';
        };

        $scope.saveEtiquetteItem = function (item, index) {
            GenericService.post(saveEtiquetteItemUrl, angular.toJson(item))
                    .then(
                            function (data) {
                                if (data.msg && data.msg.typeError && data.msg.typeError == 'INFO') {
                                    $scope.loadEtiquetteItems();
                                    $scope.displayAddEtiquetteItemButton = true;
                                }
                            },
                            function () {}
                    );
        };

        $scope.cancelEtiquetteItem = function (item, index) {
            if (item.id != null) {
                $scope.listEtiquetteItem[index] = angular.copy($scope.etiquetteItemObjectBackup);
                $scope.listEtiquetteItem[index].disabled = true;
            } else {
                $scope.listEtiquetteItem.splice(index, 1);
            }
            $scope.displayAddEtiquetteItemButton = true;
        };

        $scope.editEtiquetteItem = function (item, index) {
            $scope.listEtiquetteItem[index].disabled = false;
            $scope.displayAddEtiquetteItemButton = false;
            $scope.etiquetteItemObjectBackup = angular.copy($scope.listEtiquetteItem[index]);
        };

        $scope.removeEtiquetteItem = function (item, index) {
            if (item.id != null) {
                showConfirmation("supprimer ", function () {
                    GenericService.post(removeItemUrl, item)
                            .then(
                                    function (data) {
                                        $scope.listEtiquetteItem.splice(index, 1);
                                    },
                                    function () {}
                            );
                });
            }
        };

        $scope.loadEtiquetteItems = function () {
            if ($scope.fullObject.code != null && $scope.fullObject.type != null && $scope.fullObject.type.ideCode != null) {
                GenericService.get(getItemsUrl + "/" + $scope.fullObject.code + "?t=" + $scope.fullObject.type.ideCode)
                        .then(
                                function (data) {
                                    $scope.listEtiquetteItem = data;
                                    $scope.listEtiquetteItem.forEach(function (p) {
                                        p.dateDebutValidite = new Date(p.dateDebutValidite);
                                        p.dateFinValidite = (p.dateFinValidite == null ? null : new Date(p.dateFinValidite));
                                    });
                                },
                                function () {}
                        );
            }
        };
//================================================================================

        $scope.displayClassificationPanel = function () {
            return $scope.fullObject.statutDto.ideCode == '1' && $scope.mode == 'UP';
        };
    }]);

