insert into centers
values(default, 'Spitalul Judetean de Urgenta', 'Romania', 'Harghita', 'Miercurea Ciuc', 'Denes Laszlo', 2);




insert into vaccines
values(default, 'mRNA-1273', 'mRNA', 'Moderna', 'USA'),
(default, 'JNJ-78436735 (Ad26.COV2.S)', 'Non-Replicating Viral Vector', 'Johnson & Johnson', 'Netherlands'),
(default, ' NVX-CoV2373', 'Protein Subunit', 'Novavax', 'USA'),
(default, 'AZD1222 (ChAdOx1)', 'Non-Replicating Viral Vector', 'Oxford, AstraZeneca', 'United Kingdom'),
(default, 'J07BX03', 'Viral Vector', 'Sputnik V', 'Russia');



insert into user_schedules
values(default, 31, 1, 1, '2022-01-01 14:00:00');

select * from centers;
select * from vaccines;
select * from users;
select * from user_schedules;
