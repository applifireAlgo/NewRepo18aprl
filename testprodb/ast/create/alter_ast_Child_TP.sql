

ALTER TABLE `ast_Child_TP` ADD CONSTRAINT FOREIGN KEY (`pid`) REFERENCES `ast_Parent_T`(`pid`);



ALTER TABLE `ast_Child_TP` ADD CONSTRAINT FOREIGN KEY (`addr`) REFERENCES `ast_Address_T`(`addressId`);

