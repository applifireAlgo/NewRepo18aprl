

ALTER TABLE `ast_TestOne_M` ADD CONSTRAINT FOREIGN KEY (`gen`) REFERENCES `ast_Gender_M`(`genderId`);



ALTER TABLE `ast_TestOne_M` ADD CONSTRAINT FOREIGN KEY (`addqadd`) REFERENCES `ast_Address_T`(`addressId`);

