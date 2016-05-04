

ALTER TABLE `ast_TestThree_T` ADD CONSTRAINT FOREIGN KEY (`gen`) REFERENCES `ast_Gender_M`(`genderId`);



ALTER TABLE `ast_TestThree_T` ADD CONSTRAINT FOREIGN KEY (`addd`) REFERENCES `ast_Address_T`(`addressId`);

