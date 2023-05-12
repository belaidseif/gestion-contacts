insert into entreprises(id, tva, adresse, date_creation) values (100, 123,'Longueville','2018-10-10')
insert into entreprises(id, tva, adresse, date_creation) values (101, 253,'Dottignies','2018-10-28')
insert into entreprises(id, tva, adresse, date_creation) values (102, 65747,'Musson','2023-05-10')

insert into contacts(id, nom, prenom, adresse, type,date_creation) values (200, 'Belaid','Seif', 'Longueville', 'EMPLOYE','2020-10-10')

insert into entreprise_contacts(contact_id, entreprise_id) values (200,101)