

ALTER TABLE `ast_SFour_T` ADD CONSTRAINT FOREIGN KEY (`sds`) REFERENCES `ast_CoreContacts_T`(`contactId`);



ALTER TABLE `ast_SFour_T` ADD CONSTRAINT FOREIGN KEY (`sdfs`) REFERENCES `ast_Login_T`(`loginPk`);



ALTER TABLE `ast_SFour_T` ADD CONSTRAINT FOREIGN KEY (`fs`) REFERENCES `ast_User_T`(`userId`);



ALTER TABLE `ast_SFour_T` ADD CONSTRAINT FOREIGN KEY (`sffdf`) REFERENCES `ast_Sone_T`(`soneid`);



ALTER TABLE `ast_SFour_T` ADD CONSTRAINT FOREIGN KEY (`dfd`) REFERENCES `ast_Title_M`(`titleId`);

