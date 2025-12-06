INSERT INTO  socle.socle_fonction(code,libelle,page_uri_base,api_uri_base,parent,type_fonction,niveau_habilitation_max) 
VALUES 
('FC_ADMIN','Administration','/','/', null,'MENU','PARAM_NVX_SA'),
('FC_HABILIT','Habilitation','/','/', 'FC_ADMIN','MENU','PARAM_NVX_SA'),
('FC_PROF','Gestion des profils','/profil','/parametre/profil', 'FC_HABILIT','FONCTION','PARAM_NVX_SA'),
('FC_USER','Gestion des utilisateurs','/utilisateur','/parametre/utilisateur', 'FC_HABILIT','FONCTION','PARAM_NVX_SA'),
('FC_GROUPE','Gestion des groupes','/groupe','/parametre/groupe', 'FC_HABILIT','FONCTION','PARAM_NVX_SA')
ON CONFLICT (code) DO UPDATE 
SET
libelle= EXCLUDED.libelle,
page_uri_base =EXCLUDED.page_uri_base,
api_uri_base =EXCLUDED.api_uri_base,
parent =EXCLUDED.parent,
type_fonction =EXCLUDED.type_fonction,
niveau_habilitation_max =EXCLUDED.niveau_habilitation_max;


INSERT INTO socle.spr_w_type_workflow(
	code, dernier_numero, libelle)
	VALUES 
('WS',0, 'Workflow Système'),  
('WU',0, 'Workflow Utilisateur')

ON CONFLICT (code) DO UPDATE 
SET
libelle= EXCLUDED.libelle
;

-- 
-- 
-- 
-- -- /**LES WORKFLOWS PREPARAMETRE**/
-- 
INSERT INTO  socle.spr_w_workflows_cycle(code_type_workflow,id,numero,libelle,description,code_liste_actions,code_statut,version,created_by,last_modified_by)
VALUES 
('WS','WKFL_PROFIL','1','Cycle de vie des profils','Cycle de vie des profils','WKFL_PROFIL','20','1','SYSTEM','SYSTEM'),
('WS','WKFL_UTILISATEUR','2','Cycle de vie des utilisateurs','Cycle de vie des utilisateurs','WKFL_UTILISATEUR','20','1','SYSTEM','SYSTEM'),
('WS','WKFL_GROUPE','3','Cycle de vie des groupes','Cycle de vie des groupes','WKFL_GROUPE','20','1','SYSTEM','SYSTEM')

ON CONFLICT (id) DO UPDATE 
SET
description= EXCLUDED.description,
numero= EXCLUDED.numero,
libelle= EXCLUDED.libelle,
code_statut= EXCLUDED.code_statut,
code_type_workflow= EXCLUDED.code_type_workflow,
code_liste_actions =EXCLUDED.code_liste_actions;


INSERT INTO  socle.spr_w_etats(ide_workflow,id,ide_sequence,ide_etape,code_etat,libelle_etat,code_couleur,indic_modif_authorisee,ind_etape_validation,version,created_by,last_modified_by)
VALUES 
('WKFL_PROFIL','PROFIL001','1','1','ES','En Saisie','#A3E7FC','4','5','1','SYSTEM','SYSTEM'),
('WKFL_PROFIL','PROFIL002','1','2','OV','Actif','#26C485','5','4','1','SYSTEM','SYSTEM'),
('WKFL_PROFIL','PROFIL003','1','3','CL','Obsolète','#553A41','5','5','1','SYSTEM','SYSTEM'),

('WKFL_UTILISATEUR','UTILISATEUR001','1','1','ES','En Saisie','#A3E7FC','4','5','1','SYSTEM','SYSTEM'),
('WKFL_UTILISATEUR','UTILISATEUR002','1','2','OV','Actif','#26C485','5','4','1','SYSTEM','SYSTEM'),
('WKFL_UTILISATEUR','UTILISATEUR003','1','3','CL','Obsolète','#553A41','5','5','1','SYSTEM','SYSTEM') 

--   Workflow des écritures comptables




ON CONFLICT (id) DO UPDATE 
SET
code_couleur= EXCLUDED.code_couleur,
ide_sequence= EXCLUDED.ide_sequence,
ide_etape= EXCLUDED.ide_etape,
code_etat= EXCLUDED.code_etat,
libelle_etat = EXCLUDED.libelle_etat,
indic_modif_authorisee= EXCLUDED.indic_modif_authorisee,
ind_etape_validation= EXCLUDED.ind_etape_validation;


INSERT INTO socle.spr_w_workflow_cycle_objet(id, version, domaine_id, workflow_id)
	VALUES 
('WKFL_PROFIL','1','WKFL_PROFIL.1','WKFL_PROFIL'),
('WKFL_UTILISATEUR','1','WKFL_UTILISATEUR.1','WKFL_UTILISATEUR')

ON CONFLICT (id) DO UPDATE 
SET 
version= EXCLUDED.version,
domaine_id =EXCLUDED.domaine_id,
workflow_id =EXCLUDED.workflow_id;


INSERT INTO socle.spr_w_actions_permise(id,version, code_couleur, code_action, code_etat, code_etat_suivant, callbak_id, niveau_habilitation,indic_comment)
VALUES 
( 'PROFIL00000001', '1', '#66CDAA', 'PROFIL_1.11', 'PROFIL001', 'PROFIL002',NULL, '30','141.1.0.0'),
( 'PROFIL00000002', '1', '#97CF7A', 'PROFIL_1.12', 'PROFIL002', 'PROFIL003',NULL, '30','141.1.0.0'),

( 'UTILISATEUR00000001', '1', '#66CDAA', 'UTILISATEUR_1.11', 'UTILISATEUR001', 'UTILISATEUR002',NULL, '30','141.1.0.0'),
( 'UTILISATEUR00000002', '1', '#97CF7A', 'UTILISATEUR_1.12', 'UTILISATEUR002', 'UTILISATEUR003',NULL, '30','141.1.0.0')



ON CONFLICT (id) DO UPDATE
SET
code_couleur= EXCLUDED.code_couleur,
code_etat= EXCLUDED.code_etat,
code_etat_suivant= EXCLUDED.code_etat_suivant,
code_action= EXCLUDED.code_action,
callbak_id = EXCLUDED.callbak_id,
niveau_habilitation = EXCLUDED.niveau_habilitation;


-- Insertion des profils par défaut
INSERT INTO securite.sec_profils(id, code, intitule, description, etat, created_by, last_modified_by)
VALUES
('PROFIL_PATIENT','PT', 'Patient','Profil pour les patients du système de santé','PROFIL002','SYSTEM','SYSTEM'),
('PROFIL_PROFESSIONNEL_SANTE','PS', 'Professionnel de Santé','Profil pour les professionnels de santé du système','PROFIL002','SYSTEM','SYSTEM')

ON CONFLICT (id) DO UPDATE
SET
code= EXCLUDED.code,
intitule= EXCLUDED.intitule,
description= EXCLUDED.description,
etat= EXCLUDED.etat;