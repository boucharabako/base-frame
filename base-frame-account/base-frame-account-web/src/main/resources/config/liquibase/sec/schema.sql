/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Author:  kouwonou
 * Created: 27 juil. 2018
 */

CREATE SCHEMA  IF NOT EXISTS securite;


/**
* Structure de la table utilisateurs_profil
*
*/
-- CREATE TABLE  IF NOT EXISTS securite.sec_utilisateurs_profil
-- (
--   id character varying(40) NOT NULL,
--   created_by character varying(50),
--   created_date timestamp without time zone,
--   created_user_agent_info character varying(255),
--   last_modified_by character varying(50),
--   last_modified_date timestamp without time zone,
--   last_modified_user_agent_info character varying(255),
--   version integer NOT NULL,
--   profil_id character varying(255),
--   utilisateur_id character varying(255),
--   CONSTRAINT sec_utilisateurs_profil_pkey PRIMARY KEY (id),
--   CONSTRAINT fk4vq3ijq2r9xj4vg0wdr9y7av7 FOREIGN KEY (utilisateur_id)
--       REFERENCES securite.sec_utilisateurs (id) MATCH SIMPLE
--       ON UPDATE NO ACTION ON DELETE NO ACTION,
--   CONSTRAINT fkh95chnvbiiukvatek4a2qvm09 FOREIGN KEY (profil_id)
--       REFERENCES securite.sec_profils_utilisateurs (id_profil) MATCH SIMPLE
--       ON UPDATE NO ACTION ON DELETE NO ACTION
-- )
-- WITH (
--   OIDS=FALSE
-- );
-- ALTER TABLE securite.sec_utilisateurs_profil
--   OWNER TO postgres;
