/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
var App;
var appUrl;
App.controller('structureController', ['$scope', 'GenericService', 'PropagationService', '$rootScope', function ($scope, GenericService, PropagationService, $rootScope) {


        const loadStructureDeControllePatternUrl = appUrl + "api/parameter/ref/budgetaire/encodage/findZonesOfStructureDeContole";
        const validatorUrl = appUrl + "api/parameter/ref/budgetaire/encodage/validatorMessage";
        const validatorMessageForPreviousZoneUrl = appUrl + "api/parameter/ref/budgetaire/encodage/validatorMessageForPreviousZone";
        const zonevalidatorUrl = appUrl + "api/parameter/ref/budgetaire/encodage/zonevalidatorMessage";
        const zonePatternvalidatorUrl = appUrl + "api/parameter/ref/budgetaire/encodage/zonePatternvalidatorMessage";

        const LIBELLE_REQUIS = "LIBELLE_REQUIS";
        const TYPE_LIGNE_REQUIS = "TYPE_LIGNE_REQUIS";
        const CATEGORIE_REQUIS = "CATEGORIE_REQUIS";
        const ENCODE_KEY_REQUIS = "ENCODE_KEY_REQUIS";
        const TYPE_INDICATEUR_ASSOCIE = "TYPE_INDICATEUR_ASSOCIE";
        const TYPE_ACTEUR_REQUISE = "TYPE_ACTEUR_REQUISE";
        const FIRST_ZONE_REQUISE = "FIRST_ZONE_REQUISE";
        const PREVIOUS_ZONE_MUST_BE_FILLED = "PREVIOUS_ZONE_MUST_BE_FILLED";
        const FIRST_SOUS_ZONE_REQUISE = "FIRST_SOUS_ZONE_REQUISE";
        const ZONE_PRECEDENT_ZONE_SAISE_REQUISE = "ZONE_PRECEDENT_ZONE_SAISE_REQUISE";
        const SOUS_ZONE_PRECEDENT_SOUS_ZONE_SAISE_REQUISE = "SOUS_ZONE_PRECEDENT_SOUS_ZONE_SAISE_REQUISE";
        const PR_LINE_MUST_BE_ASSOCIETED = "PR_LINE_MUST_BE_ASSOCIETED";

        $scope.cleEncodage = "";
        $scope.stc = "";

        $scope.loadStructureDeControlePattern = function (structureDeControle, stc) {
            GenericService.get(loadStructureDeControllePatternUrl + "/" + structureDeControle)
                    .then(
                            function (data) {
                                $scope.formatEncodeKeyForPreview(data);
                            },
                            function () {
                            }
                    );
        };

        $scope.previewStructure = function (stc) {
            $scope.cleEncodage = "";
            $scope.loadStructureDeControlePattern(stc);
        };

        $scope.previewStructureName = function (id) {
            $scope.cleEncodage = "";
//            jslog("NAME "+name);
            $("#" + id).val();
            $scope.stc = $("#" + id).val();
        };


        $scope.formatEncodeKeyForPreview = function (data) {
            var previewDiv = document.getElementById('previewDiv');
            previewDiv.innerHTML = '';
            var tabindex = 0;
            var sousZoneH = document.createElement("H1");
            sousZoneH.setAttribute("id", "idAllSousZoneH");
            jslog("SIZE ZONE " + data.sc.listZone.length);
            previewDiv.appendChild(sousZoneH);
            if (data.sc.listZone.length !== 0) {
                data.sc.listZone.forEach(function (zone) {
                    if (zone.listSousZone.length === 0) {
                        // Recherche l'index de la sous zone courante
                        var index = data.sc.listZone.indexOf(zone);
                        if (zone.typeDeDonneeString != "L") {
                            // Creation des inputs
                            var textField = document.createElement("INPUT");
                            // ajout de la class form-control
                            textField.setAttribute("class", "form-control");
                            // Affectation de l'ID
                            textField.setAttribute("data-pid", zone.id);
                            // type champ
                            textField.setAttribute("data-field", zone.field);
                            // numero d'ordre
                            textField.setAttribute("data-ordre", zone.numeroZone);
                            // separateur
                            textField.setAttribute("data-separateur", zone.separateur);
                            // if (index == data.sc.listZone.length - 1)
                            textField.setAttribute("data-last-souszone", true);
                            // typeDeDonneeString
                            textField.setAttribute("type-donnee", zone.typeDeDonneeString);
                            // hasSeparateur
                            textField.setAttribute("data-has-separateur", true);
                            //textField.setAttribute("move-focus", "");
                            textField.setAttribute("tabindex", tabindex = tabindex + 1);
                            // Affectation de l'ID
                            textField.setAttribute("id", "input" + tabindex);
                            //Placeholder
                            textField.setAttribute("placeholder", zone.intitule);
                            //Field is Classification
                            textField.setAttribute("field-is-classification", zone.fieldIsClassification);
                            if (zone.fieldIsClassification && zone.fieldIsClassification == true) {
                                    textField.setAttribute("data-toggle", "modal");
                                    textField.setAttribute("data-target", "#classmodal");
                                    textField.setAttribute("readonly", "readonly");
                                    textField.setAttribute("data-ltc", zone.listDeControle);
                                    textField.setAttribute("data-listc", angular.fromJson(zone.listClasseur));
                                    textField.style["background-color"] = "white";
                                    textField.onclick = function () {
                                        loadClassification(zone.listDeControle, zone.id);
                                    };
                                }

                            // Definition du type des inputs
                            if (zone.typeDeDonneeString === "N") {
                                textField.setAttribute("type", "text");
                                textField.setAttribute("min", "0");
                                textField.setAttribute("max", getMaxNumber(zone.tailleZone));
                                // textField.setAttribute("max", getMaxNumber(zone.tailleZone + (zone.separateur.length))); // probleme de séparateur
                                textField.setAttribute("title", "Veuillez respecter le format! seul les valeurs numériques sont acceptées");
                            }
                            if (zone.typeDeDonneeString === "X") {
                                textField.setAttribute("type", "text");
                                textField.setAttribute("pattern", "[a-zA-Z0-9]+");
                                textField.setAttribute("title", "Veuillez respecter le format");
                            }
                            if (zone.typeDeDonneeString === "A") {
                                textField.setAttribute("type", "text");
                                // textField.setAttribute("pattern", "[A-Za-z]{1," + zone.tailleZone + (zone.separateur.length) + "}"); probleme de séparateur
                                textField.setAttribute("pattern", "[A-Za-z]{1," + zone.tailleZone + "}");
                                textField.setAttribute("title", "Veuillez respecter le format! seul les valeurs alphabétiques sont acceptées");
                            }
                            //definir la taille de la zone au cas ou c'est de type texte
                            // textField.setAttribute("maxlength", zone.tailleZone + (zone.separateur.length)); probleme de separateur
                            textField.setAttribute("maxlength", zone.tailleZone);
                            // definir le champs comme obligatoire
                            textField.setAttribute("required", "required");
                            textField.style.width = "150px";
                            var formGroupDiv = document.createElement("DIV");
                            formGroupDiv.setAttribute("class", "form-group col-lg-2");
                            var label = document.createElement("LABEL");
                            var labelDiv = document.createElement("DIV");
                            labelDiv.style.height = "50px";
                            label.setAttribute("class", "control-label");
                            var labelText = document.createTextNode(zone.intitule + " :");
                            labelDiv.style.fontSize = "12px";
                            labelDiv.style.paddingTop = "5px";
                            labelDiv.setAttribute("class", "nicescroll");
                            labelDiv.style.overflow = "-webkit-paged-x";
                            labelDiv.appendChild(labelText);
                            formGroupDiv.appendChild(labelDiv);
                            var inputDiv = document.createElement("DIV");
                            inputDiv.appendChild(textField);
                            formGroupDiv.appendChild(inputDiv);
                            // Ajout des inputs au Div
                            previewDiv.appendChild(formGroupDiv);
                        } else {
//========================================================================================================

                            var selectField = document.createElement("INPUT");
                            selectField.style.width = "150px";
                            selectField.setAttribute("tabindex", tabindex = tabindex + 1);
                            selectField.setAttribute("id", "input" + tabindex);
                            selectField.setAttribute("class", "form-control");
                            // Affectation de l'ID
                            selectField.setAttribute("data-pid", zone.id);
                            // type champ
                            selectField.setAttribute("data-field", zone.field);
                            // numero d'ordre
                            selectField.setAttribute("data-ordre", zone.numeroZone);
                            // separateur
                            selectField.setAttribute("data-separateur", zone.separateur);
                            // typeDeDonneeString
                            selectField.setAttribute("type-donnee", zone.typeDeDonneeString);
                            //Place holder
                            selectField.placeholder = zone.intitule;
                            if (index != data.sc.listZone.length - 1) {
                                selectField.setAttribute("data-last-zone", true);
                            }
                            selectField.setAttribute("data-has-separateur", true);

                            // required
                            selectField.setAttribute("required", "required");
                            selectField.setAttribute("maxlength", zone.tailleZone);
                            selectField.setAttribute("list", zone.id);
//                            var defaultOption = document.createElement("OPTION");
//                            defaultOption.value = "";
//                            defaultOption.innerHTML = 'Choisir';
//                            defaultOption.selected = true;
//                            defaultOption.disabled = true;
//                            selectField.appendChild(defaultOption);
                            var dataList = document.createElement("DATALIST");
                            dataList.setAttribute("id", zone.id);
                            zone.libellesDTO.forEach(function (e) {
                                var option = document.createElement("OPTION");
                                option.value = e.id;
                                option.innerHTML = e.libelle;
                                dataList.appendChild(option);
                            });
//                            selectField.onblur = "javascript:fillZone()";
//                            if (selectField.addEventListener) {  // all browsers except IE before version 9
//                                selectField.addEventListener("keyup", fillZone, false);
//                            } else {
//                                if (selectField.attachEvent) {   // IE before version 9
//                                    selectField.attachEvent("keyup", fillZone);
//                                }
//                            }
                            selectField.onchange = function () {
                                fillZone(this, zone, selectField.value);
                            };
                            selectField.onkeyup = function () {
                                fillZone(this, zone, selectField.value);
                            };
//========================================================================================================
                            var formGroupDiv = document.createElement("DIV");
                            formGroupDiv.setAttribute("class", "form-group col-lg-2");
                            var label = document.createElement("LABEL");
                            var labelDiv = document.createElement("DIV");
                            labelDiv.style.height = "50px";
                            label.setAttribute("class", "control-label");
                            var labelText = document.createTextNode(zone.intitule + " :");
                            labelDiv.style.fontSize = "12px";
                            labelDiv.style.paddingTop = "5px";
                            labelDiv.setAttribute("class", "nicescroll");
                            labelDiv.style.overflow = "-webkit-paged-x";
                            labelDiv.appendChild(labelText);
                            formGroupDiv.appendChild(labelDiv);
                            var inputDiv = document.createElement("DIV");
                            inputDiv.appendChild(selectField);
                            inputDiv.appendChild(dataList);
                            formGroupDiv.appendChild(inputDiv);
                            previewDiv.appendChild(formGroupDiv);
                        }
                        var spanDiv = document.createElement("DIV");
                        spanDiv.style.padding = "7% 1% 0px 4px";
                        if (index !== data.sc.listZone.length - 1) {
                            var span = document.createElement("SPAN");
                            var spanText = document.createTextNode(zone.separateur);
                            span.appendChild(spanText);
                            // Ajout des séparateurs au Div
                            spanDiv.appendChild(span);
                        }
                        previewDiv.appendChild(spanDiv);
                    } else {
                        // La zone a des sous zones
                        zone.listSousZone.forEach(function (sousZone) {
                            var indexSousZone = zone.listSousZone.indexOf(sousZone);
                            if (sousZone.typeDeDonneeString != "L") {
                                // Creation des inputs
                                var textField = document.createElement("INPUT");
                                //textField.setAttribute("move-focus", "");
                                textField.setAttribute("tabindex", tabindex = tabindex + 1);
                                // Affectation de l'ID
                                textField.setAttribute("id", "input" + tabindex);
                                textField.setAttribute("data-pid", sousZone.id);
                                // type champ
                                textField.setAttribute("data-field", sousZone.field);

                                if (indexSousZone == zone.listSousZone.length - 1) {
                                    textField.setAttribute("data-last-souszone", true);
                                }
                                // typeDeDonneeString
                                textField.setAttribute("type-donnee", sousZone.typeDeDonneeString);
                                // numero d'ordre
                                textField.setAttribute("data-ordre", sousZone.numeroSousZone);
                                // ajout de la class form-control
                                textField.setAttribute("class", "form-control");
                                //Placeholder
                                textField.setAttribute("placeholder", sousZone.intitule);
                                //Field Is Classification
                                textField.setAttribute("field-is-classification", sousZone.fieldIsClassification);
                                if (sousZone.fieldIsClassification && sousZone.fieldIsClassification == true) {
                                        textField.setAttribute("data-toggle", "modal");
                                        textField.setAttribute("data-target", "#classmodal");
                                        textField.setAttribute("readonly", "readonly");
                                        textField.setAttribute("data-ltc", sousZone.listDeControle);
//                                        jslog("LIST CLASSEUR "+angular.toJson(sousZone.listClasseur))
                                        textField.setAttribute("data-listc", angular.toJson(sousZone.listClasseur));
                                        textField.style["background-color"] = "white";
                                        textField.onclick = function () {
                                            loadClassification(sousZone.listDeControle, sousZone.id);
                                        };
                                    }

                                // Definition du type des inputs
                                textField.setAttribute("maxlength", sousZone.tailleSousZone);
                                // Creation des pattern en fonction du type de donnée des inputs
                                if (sousZone.typeDeDonneeString === "N") {
                                    textField.setAttribute("type", "text");
                                    textField.setAttribute("min", "0");
                                    textField.setAttribute("max", getMaxNumber(sousZone.tailleSousZone));
                                    textField.setAttribute("title", "Veuillez respecter le format ce nombre ne doit pas depasser" + sousZone.tailleSousZone);
                                }
                                if (sousZone.typeDeDonneeString === "X") {
                                    textField.setAttribute("type", "text");
                                    textField.setAttribute("pattern", "[a-zA-Z0-9]+");
                                    textField.setAttribute("title", "Veuillez respecter le format");
                                }
                                if (sousZone.typeDeDonneeString === "A") {

                                    textField.setAttribute("type", "text");
                                    textField.setAttribute("pattern", "[A-Za-z]{1," + sousZone.tailleSousZone + "}");
                                    textField.setAttribute("title", "Veuillez respecter le format! seul les valeurs alphabtiques sont acceptées");
                                }
                                textField.setAttribute("required", "required");
                                textField.style.width = "150px";
                                var formGroupDiv = document.createElement("DIV");
                                formGroupDiv.setAttribute("class", "form-group col-lg-2");
                                var label = document.createElement("LABEL");
                                var labelDiv = document.createElement("DIV");
                                labelDiv.style.height = "50px";
                                label.setAttribute("class", "control-label");
                                var labelText = document.createTextNode(sousZone.intitule + " :");
                                labelDiv.style.fontSize = "12px";
                                labelDiv.style.paddingTop = "5px";
                                labelDiv.setAttribute("class", "nicescroll");
                                labelDiv.style.overflow = "-webkit-paged-x";
                                labelDiv.appendChild(labelText);
                                formGroupDiv.appendChild(labelDiv);
                                var inputDiv = document.createElement("DIV");
                                inputDiv.appendChild(textField);
                                formGroupDiv.appendChild(inputDiv);
                                // Ajout des inputs au Div
                                previewDiv.appendChild(formGroupDiv);

                                // Gestion des séparateurs
                                // ajout de separateur au apres la derniere sous zone d'une zone
                                var spanDiv = document.createElement("DIV");
                                spanDiv.style.padding = "7% 1% 0px 4px";
                                var indexZoneCourante = data.sc.listZone.indexOf(zone);
                                if (indexSousZone == zone.listSousZone.length - 1 && indexZoneCourante != data.sc.listZone.length - 1) {

                                    //debut separateur si c'est derniere sous zone et quil ya une nouvelle zone a la suite
                                    textField.setAttribute("data-separateur", zone.separateur);
                                    inputDiv.appendChild(textField);
                                    previewDiv.appendChild(formGroupDiv);
                                    //fin
                                    var span = document.createElement("SPAN");
                                    var spanText = document.createTextNode(zone.separateur);
                                    span.appendChild(spanText);
                                    // Ajout des séparateurs au Div
                                    spanDiv.appendChild(span);
                                }
                                previewDiv.appendChild(spanDiv);

                            } else {

                                var selectField = document.createElement("INPUT");
                                selectField.style.width = "150px";
                                selectField.setAttribute("tabindex", tabindex = tabindex + 1);
                                selectField.setAttribute("id", "input" + tabindex);
                                selectField.setAttribute("class", "form-control");
                                // Affectation de l'ID
                                selectField.setAttribute("data-pid", sousZone.id);
                                // type champ
                                selectField.setAttribute("data-field", sousZone.field);
                                // numero d'ordre
                                selectField.setAttribute("data-ordre", sousZone.numeroSousZone);
                                // typeDeDonneeString
                                selectField.setAttribute("type-donnee", sousZone.typeDeDonneeString);
                                // required
                                selectField.setAttribute("required", "required");

                                //maxlength
                                selectField.setAttribute("maxlength", sousZone.tailleSousZone);
                                selectField.setAttribute("list", sousZone.id);

//                                var defaultOption = document.createElement("OPTION");
//                                defaultOption.value = "";
//                                defaultOption.innerHTML = 'Choisir';
//                                defaultOption.selected = true;
//                                defaultOption.disabled = true;
//                                selectField.appendChild(defaultOption);
//
//                                sousZone.libellesDTO.forEach(function (e) {
//                                    var option = document.createElement("OPTION");
//                                    option.value = e.id;
//                                    option.innerHTML = e.id + " " + e.libelle;
//                                    selectField.appendChild(option);
//                                });


                                var dataList = document.createElement("DATALIST");
                                dataList.setAttribute("id", sousZone.id);
                                sousZone.libellesDTO.forEach(function (e) {
                                    var option = document.createElement("OPTION");
                                    option.value = e.id;
                                    option.innerHTML = e.libelle;
                                    dataList.appendChild(option);
                                });
//                            selectField.onblur = "javascript:fillZone()";
//                            if (selectField.addEventListener) {  // all browsers except IE before version 9
//                                selectField.addEventListener("keyup", fillZone, false);
//                            } else {
//                                if (selectField.attachEvent) {   // IE before version 9
//                                    selectField.attachEvent("keyup", fillZone);
//                                }
//                            }
                                selectField.onchange = function () {
                                    fillZone(this, sousZone, selectField.value);
                                };
                                selectField.onkeyup = function () {
                                    fillZone(this, sousZone, selectField.value);
                                };


                                var formGroupDiv = document.createElement("DIV");
                                formGroupDiv.setAttribute("class", "form-group col-lg-2");
                                var label = document.createElement("LABEL");
                                var labelDiv = document.createElement("DIV");
                                labelDiv.style.height = "50px";
                                label.setAttribute("class", "control-label");
                                var labelText = document.createTextNode(sousZone.intitule + " :");
                                labelDiv.style.fontSize = "12px";
                                labelDiv.style.paddingTop = "5px";
                                labelDiv.setAttribute("class", "nicescroll");
                                labelDiv.style.overflow = "-webkit-paged-x";
                                labelDiv.appendChild(labelText);
                                formGroupDiv.appendChild(labelDiv);
                                var inputDiv = document.createElement("DIV");
                                inputDiv.appendChild(selectField);
                                inputDiv.appendChild(dataList);
                                formGroupDiv.appendChild(inputDiv);
                                previewDiv.appendChild(formGroupDiv);

                                // Gestion des séparateurs
                                // ajout de separateur au apres la derniere sous zone d'une zone
                                var spanDiv = document.createElement("DIV");
                                spanDiv.style.padding = "8% 1% 0px 4px";
                                var indexZoneCourante = data.sc.listZone.indexOf(zone);
                                if (indexSousZone === zone.listSousZone.length - 1 && indexZoneCourante !== data.sc.listZone.length - 1) {

                                    //debut separateur si c'est derniere sous zone et quil ya une nouvelle zone a la suite
                                    selectField.setAttribute("data-separateur", zone.separateur);
                                    inputDiv.appendChild(selectField);
                                    previewDiv.appendChild(formGroupDiv);
                                    //fin
                                    var span = document.createElement("SPAN");
                                    var spanText = document.createTextNode(zone.separateur);
                                    span.appendChild(spanText);
                                    // Ajout des séparateurs au Div
                                    spanDiv.appendChild(span);
                                }
                                previewDiv.appendChild(spanDiv);

                            }
                        });
                    }
                });
            } else {
                var labelEmpty = document.createElement("H4");
                labelEmpty.style.paddingLeft = "28%";
                labelEmpty.style.paddingRight = "28%";
                labelEmpty.style.fontWeight = "bold";
                var labelEmptyText = document.createTextNode("Cette structure de contrôle n'a pas de zone");
                labelEmpty.appendChild(labelEmptyText);
                previewDiv.appendChild(labelEmpty);
                previewDiv.style.textAling = "center";
            }
            /**
             * Custom validator
             */
            $("#form_id input").on("keyup change", function () {
                let elt = $(this);
                var inp = $('#form_id input')[0]; // get first zone
                if ($(inp).val().length === 0) {
                    jslog("erreur premier champ obligatoire");
                }
                var maxlength = parseInt(elt.attr("maxlength"));
                var valeurSaisie = elt.val();


//=============================================================================
                var pid = elt.attr("data-pid");
                var id = elt.attr("id");
                var ltc = elt.attr("data-ltc");
                var typeDonnee = elt.attr("type-donnee");

                var element = document.getElementById(id);
                if (element.tagName === 'INPUT') {
                    $("input[data-pid='" + pid + "' ]").prop('title', elt.val());
                }
                if (element.tagName === 'SELECT') {
                    $("select[data-pid='" + pid + "' ]").prop('title', $("#" + id + " option:selected").text());
                }

                if (typeDonnee === "CL") {
                    $scope.ltclasseur = elt.attr("data-listc");
                    $scope.listClass = angular.fromJson($scope.ltclasseur);
                    var titre = getClasseurLibelleByClassificationID($scope.listClass, ltc, elt.val())[0].libelle;
                    if (titre) {
                        $("input[data-pid='" + pid + "' ]").prop('title', titre);
                    }
                }
//=============================================================================


                if (maxlength !== valeurSaisie.length && valeurSaisie.length != 0) {
                    elt.css("border", "1px solid red");
                    jslog("champ INVALID " + elt.attr("placeholder"));
                } else {
                    elt.css("border", "1px solid lightgrey");
                    jslog("champ VALID " + elt.attr("placeholder"));
                }
                // showAllDetail();
            });


            /**
             * check if previous field is not fill and current field is not the first
             */
            $("#form_id input").on("blur", function () {
                let elt = $(this);
                var id = elt.attr("data-pid");
                if (elt.val().length > 0) {

                    var hisId = elt.attr("data-pid");
                    var placeholder = elt.attr("placeholder");
                    //jslog(placeholder + " is empty and his ID is :" + hisId);
                }
//                showAllDetail();
            });
            /**
             * Controle input if type of input is number
             */
            $("#form_id input").on("keyup change", function () {
                let elt = $(this);
                let mx = parseInt(elt.attr("max"));
                let maxLenght = parseInt(elt.attr("maxlength"));
                let valeur = parseInt(elt.val());
                if (valeur > mx) {
                    let vv = valeur.toString();
                    vv = vv.slice(0, maxLenght);
                    elt.val(parseInt(vv));
                }
            });
            /**
             * Move Cursor to next field
             */
            $("#form_id input").on('input, keydown', function () {
                let elt = $(this);
                var tabindex = parseInt(elt.attr("tabindex"));
                var maxlength = parseInt(elt.attr("maxlength"));
                elt.on('input, keydown', function (e) {
                    var val = elt.val(), code = e.which || e.keyCode;
                    if (val.length === maxlength && [8, 37, 38, 39, 40, 46].indexOf(code) === -1) {
                        var next = document.querySelectorAll('#input' + (tabindex + 1));
                        next.length && next[0].focus();
                        return;
                    }
                    if ((val.length === 1 && code === 8) || (val.length === 0 && code === 8)) {
                        var prev = document.querySelectorAll('#input' + (tabindex - 1));
                        e.preventDefault();
                        elt.val(val.substring(1));
                        prev.length && prev[0].focus();
                        return;
                    }
                });
            });
            function getMaxNumber(taille) {
                var number = (Math.pow(10, taille) - 1);
                return number;
            }
        };

        function fillZone(elt, zone, valeur) {

            if (!getListId(zone).includes(valeur)) {
                $(elt).val("");
                GenericService.frontValidationMessages("DANGER", "Veuillez choisir parmis les valeurs suivante: " + getListIdString(zone), "L'action n'a pu être exécutée, car il s'est produit des erreurs de validation");
            }
        }

        /**
         * List des ID par rapport a lobject
         * @param {type} zone
         * @returns {Array}
         */
        function getListId(zone) {
            var idList = [];
            zone.libellesDTO.forEach(function (v) {
                idList.push(v.id);
            });
            return idList;
        }

        function getListIdString(zone) {
            var idList = [];
            zone.libellesDTO.forEach(function (v) {
                idList.push("" + v.id + "");
            });
            return idList;
        }

        function checkFieldType(champs, typeDonnee, text) {
            var regex = "";
            if (typeDonnee === 'X') {//Alphanumeric
                regex = "^[a-zA-Z0-9]+$";
                if (text.match(regex)) {
                    return true;
                } else {
                    //Message d'erreur
                    GenericService.frontValidationMessages("DANGER", champs + " ne doit contenir que des valeurs alphanumériques ", "L'action n'a pu être exécutée, car il s'est produit des erreurs de validation");
                    return false;
                }
            }
            if (typeDonnee === 'N') {//number
                regex = "\\d+";
                if (text.match(regex)) {
                    return true;
                } else {
                    GenericService.frontValidationMessages("DANGER", champs + " ne doit contenir que des valeurs numériques ", "L'action n'a pu être exécutée, car il s'est produit des erreurs de validation");
                    return false;
                }
            }
            if (typeDonnee === 'A') {//Alphabetic
                regex = "^[a-zA-Z]+$";
                if (text.match(regex)) {
                    return true;
                } else {
                    GenericService.frontValidationMessages("DANGER", champs + " ne doit contenir que des valeurs Alphabétiques ", "L'action n'a pu être exécutée, car il s'est produit des erreurs de validation");
                    return false;
                }
            }
        }

        $scope.showAllDetailStructure = function () {
            $scope.cleEncodage = "";
            var allinput = $('#form_id input, #form_id select'); // AllZones
            var finalEncode = "";

            var firstZoneOfEncodeKey = allinput[0]; // firstZoneOfEncodeKey
            var firstZoneVal = $(firstZoneOfEncodeKey).val();
            if (firstZoneVal == null || firstZoneVal.trim().length == 0) {
                GenericService.post(validatorUrl + "/" + FIRST_ZONE_REQUISE)
                        .then(
                                function () {},
                                function () {}
                        );
                return;
            }

            for (var i = 0; i <= allinput.size() - 1; i++) {
                var currentVal = $(allinput[i]).val();
                var nextVal = $(allinput[i + 1]).val();

//                if ($(allinput[i]).val().length != 0 && (typeof $(allinput[i + 1]).val() != 'undefined' && $(allinput[i + 1]).val().length == 0)) {
                if ((currentVal != null && currentVal.length != 0) && (typeof nextVal != 'undefined' && (nextVal == null || nextVal.length == 0))) {


                    for (var j = i + 1; j <= (allinput.size() - 1); j++) {
                        var currentJval = $(allinput[j]).val();
                        //if (typeof $(allinput[j]).val() != 'undefined' && $(allinput[j]).val().length != 0) {
                        if (currentJval != null && typeof currentJval != 'undefined' && (currentJval != null && currentJval.length != 0)) {
                            var type = $(allinput[j]).attr("data-field");
                            var id = $(allinput[j]).attr("data-pid");
                            GenericService.post(validatorMessageForPreviousZoneUrl + "/" + id + "/" + type)
                                    .then(
                                            function (data) {
                                                if (typeof data.msg != "undefined" && data.msg.statut == '400') {
                                                    return;
                                                }
                                            },
                                            function () {}
                                    );
                            return;
                        }
                    }
                }
            }

            for (var i = 0; i <= allinput.size() - 1; i++) {
                var currentValue = $(allinput[i]).val();
                // if ($(allinput[i]).val().length != 0 && ($(allinput[i]).val().length != $(allinput[i]).attr("maxlength"))) {
                if ((currentValue != null && currentValue.length != 0) && (currentValue != null && $(allinput[i]).attr("maxlength") != null && (currentValue.length != $(allinput[i]).attr("maxlength")))) {
                    var id = $(allinput[i]).attr("data-pid");
                    var champs = $(allinput[i]).attr("data-field");

                    GenericService.get(zonevalidatorUrl + "/" + champs + "/" + id)
                            .then(
                                    function () {},
                                    function () {}
                            );
                    return;
                }
            }

            for (var i = 0; i <= allinput.size() - 1; i++) {
                $scope.champsObject = {id: null, champs: null, text: null};
                var actualFieldValue = $(allinput[i]).val();
                // if ($(allinput[i]).val().length != 0 && ($(allinput[i]).val().length == $(allinput[i]).attr("maxlength"))) {
                if ((actualFieldValue != null && actualFieldValue.length != 0) && (actualFieldValue != null && $(allinput[i]).attr("maxlength") != null && (actualFieldValue.length == $(allinput[i]).attr("maxlength")))) {
                    var id = $(allinput[i]).attr("data-pid");
                    var champs = $(allinput[i]).attr("data-field");
                    var placeholder = $(allinput[i]).attr("placeholder");
                    var typeDonnee = $(allinput[i]).attr("type-donnee");
                    var text = $(allinput[i]).val();
                    var type = $(allinput[i]).attr("type");
                    $scope.champsObject.id = id;
                    $scope.champsObject.champs = champs;
                    $scope.champsObject.text = text;
                    jslog("GHG " + text);
                    if (checkFieldType(placeholder, typeDonnee, text) === false) {
                        return;
                    }
                }
            }

            for (i = 0; i <= allinput.size() - 1; i++) {
                var index = allinput.index(allinput[i]);
                var vc = $(allinput[i]).val();
                var vn = $(allinput[i + 1]).val();
                if ((vc !== '') && vn !== '') {
                    if ((index != (allinput.size() - 1)) && typeof $(allinput[i]).attr("data-separateur") !== "undefined") {
                        finalEncode += $(allinput[i]).val() + $(allinput[i]).attr("data-separateur");
                    } else {
                        finalEncode += $(allinput[i]).val();
                    }
                }
                if ($(allinput[i]).val() !== '' && $(allinput[i + 1]).val() == '') {
                    finalEncode += $(allinput[i]).val();
                }
            }
            $scope.cleEncodage = finalEncode;
            return false;
        };

        $scope.selectedClassification = null;
        $scope.currentInputId = null;

        function loadClassification(id, inputId) {
            $scope.selectedClassification = id;
            $scope.currentInputId = inputId;
            $('#classifTree').jstree({
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
                            var url = $('#marbre_noeud_class').attr('href');
                            jslog("ID " + $scope.selectedClassification);
                            return url + "api/parametre/conf/structurecontrole/load-classification-by-code/" + $scope.selectedClassification;
                        },
                        'data': function (node) {
                            return {'id': node.id, 'cla': $scope.claId};
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

        }


        $("#classifTree").on("select_node.jstree", function (evt, data) {
            if (data.node.id != null) {
                jslog("CURRENT INPUT ID " + $scope.currentInputId);
                $("input[data-pid='" + $scope.currentInputId + "' ]").val(data.node.original.code);
                $("input[data-pid='" + $scope.currentInputId + "' ]").prop('title', data.node.text);
            }
            if (!$scope.$$phase) {
                $scope.$apply();
            }
        });
        $("#classifTree").on("deselect_node.jstree", function (evt, data) {
            $("input[data-pid='" + $scope.currentInputId + "' ]").val(null);
            $("input[data-pid='" + $scope.currentInputId + "' ]").prop('title', '');
            $("#" + $scope.currentInputId).val(null);
        });

        $('#classmodal').on('show.bs.modal', function () {
            // do something… deselect all node;
            $("#classifTree").jstree("deselect_all");
            $('#classifTree').jstree("refresh");
            if (!$scope.$$phase) {
                $scope.$apply();
            }
        });

        $('#classmodal').on('hidden.bs.modal', function () {
            if (!$scope.$$phase) {
                $scope.$apply();
            }
        });

        $scope.dismissClassModal = function () {
            $('#classmodal').modal('hide');
        };

        function getClasseurLibelleByClassificationID(list, idClassification, idClasseur) {
            return list.filter(function (e) {
                return e.autre && e.autre === idClassification && e.id === idClasseur;
            });
        }

    }]);

