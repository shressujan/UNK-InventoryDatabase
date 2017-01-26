DROP PROCEDURE AccountAdd;
DROP PROCEDURE AccountDelete;
DROP PROCEDURE AccountUpdate;
DROP PROCEDURE AddressAdd;
DROP PROCEDURE AddressDelete;
DROP PROCEDURE AddressUpdate;
DROP PROCEDURE BrandAdd;
DROP PROCEDURE BrandDelete;
DROP PROCEDURE BrandUpdate;
DROP PROCEDURE ComputerAdd;
DROP PROCEDURE ComputerDelete;
DROP PROCEDURE ComputerUpdate;
DROP PROCEDURE IMacAdd;
DROP PROCEDURE IMacDelete;
DROP PROCEDURE IMacUpdate;
DROP PROCEDURE LaptopAdd;
DROP PROCEDURE LaptopDelete;
DROP PROCEDURE LaptopUpdate;
DROP PROCEDURE ModelAdd;
DROP PROCEDURE ModelDelete;
DROP PROCEDURE ModelUpdate;
DROP PROCEDURE OperatingSystemAdd;
DROP PROCEDURE OperatingSystemDelete;
DROP PROCEDURE PCAdd;
DROP PROCEDURE PCDelete;
DROP PROCEDURE PCUpdate;
DROP PROCEDURE OperatingSystemUpdate;
DROP PROCEDURE Role_TypeAdd;
DROP PROCEDURE Role_TypeDelete;
DROP PROCEDURE Role_TypeUpdate;
DROP PROCEDURE UserAdd;
DROP PROCEDURE UserDelete;
DROP PROCEDURE UserUpdate;



DELIMITER $$

CREATE PROCEDURE AccountAdd(
	IN ACCOUNTNAME VARCHAR(100),
	IN PASS_WORD VARCHAR(45),
	IN ISACTIVE TINYINT,
	IN COMPUTERNAME VARCHAR(100),
	OUT error_msg VARCHAR (80))
	BEGIN

		DECLARE 	rowCount INT;
		SET 		rowCount = 0;
		
		 SELECT COUNT(*) INTO rowCount
			FROM Account A
			WHERE A.AccountName =  ACCOUNTNAME;
			
		IF rowCount > 0 THEN
			SET error_msg = 'Account Already Exists!!';
		ELSE
		/*ADD NEW Account DATA*/
			INSERT INTO Account (AccountName, Pass_word, IsActive, ComputerName)
			VALUES (ACCOUNTNAME, PASS_WORD, ISACTIVE, COMPUTERNAME);
			
            SET 		rowCount = 0;
		
			SELECT COUNT(*) INTO rowCount
				FROM Account A
				WHERE A.AccountName =  ACCOUNTNAME;
			 
			/*MAKE SURE THE CUSTOMER IS SUCCESSFULLY ADDED*/
				
				IF rowCount = 1 THEN
					SET error_msg = 'New Account Successfully Added';
				ELSE
					SET error_msg = 'Account Insert Error!!';
				END IF;
		END IF;
		
		
		
		
END$$    


DELIMITER $$

CREATE PROCEDURE AccountDelete(
	IN ACCOUNTNAME VARCHAR(100),
	OUT error_msg VARCHAR (80) )
	BEGIN

		DECLARE 	rowCount INT;
		SET 		rowCount = 0;
		
		 SELECT COUNT(*) INTO rowCount
			FROM Account A
			WHERE A.AccountName =  ACCOUNTNAME;
			
		IF rowCount > 0 THEN
		/*Delete Account DATA*/
			DELETE FROM Account 
			WHERE Account.AccountName = ACCOUNTNAME;
			SET error_msg = 'Account Deleted!!';
		END IF;
		
		SET 		rowCount = 0;
		
		SELECT COUNT(*) INTO rowCount
			FROM Account A
			WHERE A.AccountName =  ACCOUNTNAME;
		 
		/*MAKE SURE THE Account IS SUCCESSFULLY Deleted*/
			
			IF rowCount = 0 THEN
				SET error_msg = ' Account Successfully Deleted!!';
			ELSE
				SET error_msg = 'Account Delete Error!!';
			END IF;
		
		
END$$  



DELIMITER $$

CREATE PROCEDURE AccountUpdate(
	IN ACCOUNTNAME VARCHAR(100),
	IN PASS_WORD VARCHAR(45),
	IN ISACTIVE TINYINT,
	IN COMPUTERNAME VARCHAR(100),
	OUT error_msg VARCHAR (80))
	BEGIN

		DECLARE 	rowCount INT;
		SET 		rowCount = 0;
		
		 SELECT COUNT(*) INTO rowCount
			FROM Account A
			WHERE A.AccountName =  ACCOUNTNAME;
			
		IF rowCount > 0 THEN
			
			UPDATE Account SET Pass_word = PASS_WORD
            WHERE Account.AccountName = ACCOUNTNAME;
            
            UPDATE Account SET IsActive = ISACTIVE
            WHERE Account.AccountName = ACCOUNTNAME;
			
            UPDATE Account SET ComputerName = COMPUTERNAME
            WHERE Account.AccountName = ACCOUNTNAME;
		
            SET 		rowCount = 0;
		
			SELECT COUNT(*) INTO rowCount
				FROM Account A
				WHERE A.AccountName =  ACCOUNTNAME;
			 
			/*MAKE SURE THE CUSTOMER IS SUCCESSFULLY ADDED*/
				
				IF rowCount = 1 THEN
					SET error_msg = 'New Account Successfully Updated';
				ELSE
					SET error_msg = 'Account Update Error!!';
				END IF;
		END IF;
		
		
		
		
END$$    
  



DELIMITER $$

CREATE PROCEDURE AddressAdd(
	IN ROOM_NUM VARCHAR(45),
	IN DEPARTMENT VARCHAR(45),
	IN BUILDINGNAME VARCHAR(45),
	IN ADDRESSDESCRIPTION VARCHAR(80),
	OUT error_msg VARCHAR (80) )
	BEGIN

		DECLARE 	rowCount INT;
		SET 		rowCount = 0;
		
		 SELECT COUNT(*) INTO rowCount
			FROM Address A
			WHERE A.Room_Num =  ROOM_NUM;
			
		IF rowCount > 0 THEN
			SET error_msg = 'Address Already Exists!!';
		ELSE
		/*ADD NEW Address DATA*/
			INSERT INTO Address (Room_Num, Department, BuildingName, AddressDescription)
			VALUES (ROOM_NUM, DEPARTMENT, BUILDINGNAME, ADDRESSDESCRIPTION);
			
            SET 		rowCount = 0;
		
			SELECT COUNT(*) INTO rowCount
				FROM Address A
				WHERE  A.Room_Num =  ROOM_NUM;
			 
			/*MAKE SURE THE Address IS SUCCESSFULLY ADDED*/
				
				IF rowCount = 1 THEN
					SET error_msg = 'New Address Successfully Added';
				ELSE
					SET error_msg = 'Address Insert Error!!';
				END IF;
		END IF;
		
		
		
		
END$$    








DELIMITER $$

CREATE PROCEDURE AddressDelete(
	IN ROOM_NUM VARCHAR(45),
	OUT error_msg VARCHAR (80) )
	BEGIN

		DECLARE 	rowCount INT;
		SET 		rowCount = 0;
		
		 SELECT COUNT(*) INTO rowCount
			FROM Address A
			WHERE A.Room_Num =  ROOM_NUM;
			
		IF rowCount > 0 THEN
		/*Delete the Address DATA*/
			DELETE FROM Address 
			WHERE Address.Room_Num = ROOM_NUM;
			SET error_msg = 'Address Deleted!!';
		END IF;
		
		SET 		rowCount = 0;
		
		SELECT COUNT(*) INTO rowCount
			FROM Address A
			WHERE  A.Room_Num =  ROOM_NUM;
		 
		/*MAKE SURE THE Address IS SUCCESSFULLY Deleted*/
			
			IF rowCount = 0 THEN
				SET error_msg = 'Address Successfully Deleted';
			ELSE
				SET error_msg = 'Address Delete Error!!';
			END IF;
		
		
END$$    

/*DROP TRIGGER IF EXISTS trg_updateAddress;*/

DELIMITER $$

CREATE TRIGGER trg_updateAddress BEFORE DELETE
	ON Address
    FOR EACH ROW 
    
    BEGIN
    
    DECLARE 	rowCount INT;
    
	SET 		rowCount = 0;
    
    SELECT COUNT(*) INTO rowCount
		FROM Computer C
		WHERE C.Room_Num=  old.Room_Num;
    
    IF rowCount > 0 THEN
    UPDATE Computer
		SET Computer.Room_Num = '0'
        WHERE Computer.Room_Num = old.Room_Num;
        
	END IF;
END $$ 

DELIMITER ;



DELIMITER $$

CREATE PROCEDURE AddressUpdate(
	IN ROOM_NUM VARCHAR(45),
	IN DEPARTMENT VARCHAR(45),
	IN BUILDINGNAME VARCHAR(45),
	IN ADDRESSDESCRIPTION VARCHAR(80),
	OUT error_msg VARCHAR (80) )
	BEGIN

		DECLARE 	rowCount INT;
		SET 		rowCount = 0;
		
		 SELECT COUNT(*) INTO rowCount
			FROM Address A
			WHERE A.Room_Num =  ROOM_NUM;
			
		IF rowCount > 0 THEN
			
			UPDATE Address SET Department = DEPARTMENT
            WHERE Address.Room_Num =  ROOM_NUM;
            UPDATE Address SET BuildingName = BUILDINGNAME
            WHERE Address.Room_Num =  ROOM_NUM;
			UPDATE Address SET AddressDescription = ADDRESSDESCRIPTION
            WHERE Address.Room_Num =  ROOM_NUM;
			
            SET 		rowCount = 0;
		
			SELECT COUNT(*) INTO rowCount
				FROM Address A
				WHERE  A.Room_Num =  ROOM_NUM;
			 
			/*MAKE SURE THE Address IS SUCCESSFULLY Updated*/
				
				IF rowCount = 1 THEN
					SET error_msg = 'New Address Successfully Updated';
				ELSE
					SET error_msg = 'Address Update Error!!';
				END IF;
		END IF;
		
		
		
		
END$$    




DELIMITER $$

CREATE PROCEDURE BrandAdd(
	IN BrandID VARCHAR(45),
	IN BrandDescription VARCHAR(80),

	OUT error_msg VARCHAR (80) )
	BEGIN

		DECLARE 	rowCount INT;
		SET 		rowCount = 0;
		
		 SELECT COUNT(*) INTO rowCount
			FROM Brand B
			WHERE B.BrandID =  BrandID;
			
		IF rowCount > 0 THEN
			SET error_msg = 'Brand Already Exists!!';
		ELSE
		/*ADD NEW Brand DATA*/
			INSERT INTO Brand (BrandID, BrandDescription)
			VALUES (BrandID, BrandDescription);
			
            SET 		rowCount = 0;		
		
			 SELECT COUNT(*) INTO rowCount
				FROM Brand B
				WHERE B.BrandID =  BrandID;
			 
			/*MAKE SURE THE Brand IS SUCCESSFULLY ADDED*/
				
				IF rowCount = 1 THEN
					SET error_msg = 'New Brand Successfully Added';
				ELSE
					SET error_msg = 'Brand Insert Error!!';
				END IF;
		
		END IF;
		
		
		
END$$    





DELIMITER $$

CREATE PROCEDURE BrandDelete(
	IN BrandID VARCHAR(45),
	OUT error_msg VARCHAR (80) )
	BEGIN

		DECLARE 	rowCount INT;
		SET 		rowCount = 0;
		
		 SELECT COUNT(*) INTO rowCount
			FROM Brand B
			WHERE B.BrandID =  BrandID;
			
		IF rowCount > 0 THEN
		/*Delete the Brand DATA*/
			DELETE FROM Brand 
			WHERE Brand.BrandID =  BrandID;
			SET error_msg = 'Brand Deleted!!';
		END IF;
		
		SET 		rowCount = 0;
		
		
		 SELECT COUNT(*) INTO rowCount
			FROM Brand B
			WHERE B.BrandID =  BrandID;
		 
		/*MAKE SURE THE Brand IS SUCCESSFULLY Deleted*/
			
			IF rowCount = 0 THEN
				SET error_msg = 'New Brand Successfully Deleted';
			ELSE
				SET error_msg = 'Brand Delete Error!!';
			END IF;
		
		
END$$    


DELIMITER $$

CREATE TRIGGER trg_updateBrand BEFORE DELETE
	ON Brand
    FOR EACH ROW 
    
    BEGIN
    
    DECLARE 	rowCount INT;
    
	SET 		rowCount = 0;
    
    SELECT COUNT(*) INTO rowCount
		FROM Brand B
		WHERE B.BrandID=  old.BrandID;
    
    IF rowCount > 0 THEN
    UPDATE Model
		SET Model.BrandID = 'Unspecified'
        WHERE Model.BrandID = old.BrandID;
     
	END IF;
END $$ 

DELIMITER ;


DELIMITER $$

CREATE PROCEDURE BrandUpdate(
	IN BrandID VARCHAR(45),
	IN BrandDescription VARCHAR(80),

	OUT error_msg VARCHAR (80) )
	BEGIN

		DECLARE 	rowCount INT;
		SET 		rowCount = 0;
		
		 SELECT COUNT(*) INTO rowCount
			FROM Brand B
			WHERE B.BrandID =  BrandID;
			
		IF rowCount > 0 THEN
			UPDATE Brand SET Brand.BrandDescription = BrandDescription
            WHERE Brand.BrandID =  BrandID;
			
            SET 		rowCount = 0;		
		
			 SELECT COUNT(*) INTO rowCount
				FROM Brand B
				WHERE B.BrandID =  BrandID;
			 
			/*MAKE SURE THE Brand IS SUCCESSFULLY Updated*/
				
				IF rowCount = 1 THEN
					SET error_msg = 'Brand Successfully Updated';
				ELSE
					SET error_msg = 'Brand Update Error!!';
				END IF;
		
		END IF;
		
		
		
END$$  





DELIMITER $$

CREATE PROCEDURE ComputerAdd(
	IN ComputerName VARCHAR(45),
	IN UserID INT,
    IN Room_Num VARCHAR(45),
    IN ComputerPassword VARCHAR(45),
    IN ComputerAssignedDate DATE,
	IN ComputerReturnedDate DATE,
    IN IsUpToDate TINYINT,
	OUT error_msg VARCHAR (80) )
	BEGIN

		DECLARE 	rowCount INT;
		SET 		rowCount = 0;
		
		 SELECT COUNT(*) INTO rowCount
			FROM Computer C
			WHERE C.ComputerName =  ComputerName;
			
		IF rowCount > 0 THEN
			SET error_msg = 'Computer Already Exists!!';
		ELSE
		/*ADD NEW Computer DATA*/
			INSERT INTO Computer (ComputerName, UserID, Room_Num, ComputerPassword, ComputerAssignedDate, ComputerReturnedDate, IsUpToDate)
			VALUES (ComputerName, UserID, Room_Num, ComputerPassword, ComputerAssignedDate, ComputerReturnedDate, IsUpToDate);
			
            SET 		rowCount = 0;
		
			SELECT COUNT(*) INTO rowCount
				FROM Computer C
				WHERE C.ComputerName =  ComputerName;
			 
			/*MAKE SURE THE Computer IS SUCCESSFULLY ADDED*/
				
				IF rowCount = 1 THEN
					SET error_msg = 'New Computer Successfully Added';
				ELSE
					SET error_msg = 'Computer Insert Error!!';
				END IF;
		
		END IF;
		
		
		
END$$   



DELIMITER $$

CREATE PROCEDURE ComputerDelete(
	IN ComputerName VARCHAR(45),
	OUT error_msg VARCHAR (80) )
	BEGIN

		DECLARE 	rowCount INT;
		SET 		rowCount = 0;
		
		 SELECT COUNT(*) INTO rowCount
			FROM Computer C
			WHERE C.ComputerName =  ComputerName;
			
		IF rowCount > 0 THEN
			
		/*Delete  Computer DATA*/
			DELETE FROM Computer 
			WHERE Computer.ComputerName =  ComputerName;
			SET error_msg = 'Computer Deleted!!';
		END IF;
		
		SET 		rowCount = 0;
		
		
		SELECT COUNT(*) INTO rowCount
			FROM Computer C
			WHERE C.ComputerName =  ComputerName;
		 
		/*MAKE SURE THE Computer IS SUCCESSFULLY Deleted*/
			
			IF rowCount = 0 THEN
				SET error_msg = 'Computer Successfully Deleted';
			ELSE
				SET error_msg = 'Computer Delete Error!!';
			END IF;
		
		
END$$   

DELIMITER $$

CREATE TRIGGER trg_updateComputer BEFORE DELETE
	ON Computer
    FOR EACH ROW 
    
    BEGIN
    
    DECLARE 	rowCount INT;
    
	SET 		rowCount = 0;
    
    SELECT COUNT(*) INTO rowCount
		FROM Computer C
		WHERE C.ComputerName=  old.ComputerName;
    
    IF rowCount > 0 THEN
    /*Delete the IMac DATA*/
			DELETE FROM IMac 
			WHERE IMac.ComputerName =  old.ComputerName;
	/*Delete the PC DATA*/
			DELETE FROM PC 
			WHERE PC.ComputerName =  old.ComputerName;        
	/*Delete the Laptop DATA*/
			DELETE FROM Laptop 
			WHERE Laptop.ComputerName =  old.ComputerName;    
	END IF;
END $$ 

DELIMITER ;

DELIMITER $$

CREATE PROCEDURE ComputerUpdate(
	IN ComputerName VARCHAR(45),
	IN UserID INT,
    IN Room_Num VARCHAR(45),
    IN ComputerPassword VARCHAR(45),
    IN ComputerAssignedDate DATE,
	IN ComputerReturnedDate DATE,
    IN IsUpToDate TINYINT,
	OUT error_msg VARCHAR (80) )
	BEGIN

		DECLARE 	rowCount INT;
		SET 		rowCount = 0;
		
		 SELECT COUNT(*) INTO rowCount
			FROM Computer C
			WHERE C.ComputerName =  ComputerName;
			
		IF rowCount > 0 THEN
			UPDATE Computer	 SET Room_Num = Room_Num
            WHERE Computer.ComputerName = ComputerName;
            
            UPDATE Computer	 SET UserID = UserID
            WHERE Computer.ComputerName = ComputerName;
            
            
            UPDATE Computer	 SET ComputerPassword = ComputerPassword
            WHERE Computer.ComputerName = ComputerName;
            
            
			UPDATE Computer	 SET ComputerAssignedDate = ComputerAssignedDate
            WHERE Computer.ComputerName = ComputerName;
            
            UPDATE Computer	 SET ComputerReturnedDate = ComputerReturnedDate
            WHERE Computer.ComputerName = ComputerName;
            
            UPDATE Computer	 SET IsUpToDate = IsUpToDate
            WHERE Computer.ComputerName = ComputerName;
            
            
            
            SET 		rowCount = 0;
		
			SELECT COUNT(*) INTO rowCount
				FROM Computer C
				WHERE C.ComputerName =  ComputerName;
			 
			/*MAKE SURE THE Computer IS SUCCESSFULLY Updated*/
				
				IF rowCount = 1 THEN
					SET error_msg = 'Computer Successfully Updated';
				ELSE
					SET error_msg = 'Computer Update Error!!';
				END IF;
		
		END IF;
		
		
		
END$$  





DELIMITER $$

CREATE PROCEDURE IMacAdd(
	IN ComputerName VARCHAR(45),
	IN RetinaDisplay TINYINT,
    IN ModelID VARCHAR(45),
    IN OS_ID VARCHAR(45),
	OUT error_msg VARCHAR (80) )
	BEGIN

		DECLARE 	rowCount INT;
		SET 		rowCount = 0;
		
		 SELECT COUNT(*) INTO rowCount
			FROM IMac I
			WHERE I.ComputerName =  ComputerName;
			
		IF rowCount > 0 THEN
			SET error_msg = 'IMac Already Exists!!';
		ELSE
		/*ADD NEW IMac DATA*/
			INSERT INTO IMac (ComputerName, RetinaDisplay, ModelID, OS_ID)
			VALUES (ComputerName, RetinaDisplay, ModelID, OS_ID);
			
            SET 		rowCount = 0;
		
			SELECT COUNT(*) INTO rowCount
				FROM IMac I
				WHERE I.ComputerName =  ComputerName;
			 
			/*MAKE SURE THE IMac IS SUCCESSFULLY ADDED*/
				
				IF rowCount = 1 THEN
					SET error_msg = 'New IMac Successfully Added';
				ELSE
					SET error_msg = 'IMac Insert Error!!';
				END IF;
		
		END IF;
		
		
		
END$$   




DELIMITER $$

CREATE PROCEDURE IMacDelete(
	IN ComputerName VARCHAR(45),
	OUT error_msg VARCHAR (80) )
	BEGIN

		DECLARE 	rowCount INT;
		SET 		rowCount = 0;
		
		 SELECT COUNT(*) INTO rowCount
			FROM IMac I
			WHERE I.ComputerName =  ComputerName;
			
		IF rowCount > 0 THEN
			
		/*Delete the IMac DATA*/
			DELETE FROM IMac 
			WHERE IMac.ComputerName =  ComputerName;
			SET error_msg = 'IMac Deleted!!';
		END IF;
		
		SET 		rowCount = 0;
		
		
		SELECT COUNT(*) INTO rowCount
			FROM IMac I
			WHERE I.ComputerName =  ComputerName;
		 
		/*MAKE SURE THE IMac IS SUCCESSFULLY Deleted*/
			
			IF rowCount = 0 THEN
				SET error_msg = 'IMac Successfully Deleted';
			ELSE
				SET error_msg = 'IMac Delete Error!!';
			END IF;
		
		
END$$   





DELIMITER $$

CREATE PROCEDURE IMacUpdate(
	IN ComputerName VARCHAR(45),
	IN RetinaDisplay TINYINT,
    IN ModelID VARCHAR(45),
    IN OS_ID VARCHAR(45),
	OUT error_msg VARCHAR (80) )
	BEGIN

		DECLARE 	rowCount INT;
		SET 		rowCount = 0;
		
		 SELECT COUNT(*) INTO rowCount
			FROM IMac I
			WHERE I.ComputerName =  ComputerName;
			
		IF rowCount > 0 THEN
			UPDATE IMac SET RetinaDisplay = RetinaDisplay
            WHERE IMac.ComputerName = ComputerName;
            
            UPDATE IMac SET ModelID = ModelID
            WHERE IMac.ComputerName = ComputerName;
            
            UPDATE IMac SET OS_ID = OS_ID
            WHERE IMac.ComputerName = ComputerName;
		
			
            SET 		rowCount = 0;
		
			SELECT COUNT(*) INTO rowCount
				FROM IMac I
				WHERE I.ComputerName =  ComputerName;
			 
			/*MAKE SURE THE IMac IS SUCCESSFULLY Updated*/
				
				IF rowCount = 1 THEN
					SET error_msg = 'IMac Successfully Updated';
				ELSE
					SET error_msg = 'IMac Update Error!!';
				END IF;
		
		END IF;
		
		
		
END$$   




DELIMITER $$

CREATE PROCEDURE LaptopAdd(
	IN ComputerName VARCHAR(45),
	IN Size INT,
    IN ModelID VARCHAR(45),
    IN OS_ID VARCHAR(45),
	OUT error_msg VARCHAR (80) )
	BEGIN

		DECLARE 	rowCount INT;
		SET 		rowCount = 0;
		
		 SELECT COUNT(*) INTO rowCount
			FROM Laptop L
			WHERE L.ComputerName =  ComputerName;
			
		IF rowCount > 0 THEN
			SET error_msg = 'Laptop Already Exists!!';
		ELSE
		/*ADD NEW Laptop DATA*/
			INSERT INTO Laptop (ComputerName, Size, ModelID, OS_ID)
			VALUES (ComputerName, Size, ModelID, OS_ID);
			
            SET 		rowCount = 0;
			
			SELECT COUNT(*) INTO rowCount
				FROM Laptop L
				WHERE L.ComputerName =  ComputerName;
			 
			/*MAKE SURE THE Laptop IS SUCCESSFULLY ADDED*/
				
				IF rowCount = 1 THEN
					SET error_msg = 'New Laptop Successfully Added';
				ELSE
					SET error_msg = 'Laptop Insert Error!!';
				END IF;
		END IF;
		
		
		
		
END$$     


DELIMITER $$

CREATE PROCEDURE LaptopDelete(
	IN ComputerName VARCHAR(45),
	OUT error_msg VARCHAR (80) )
	BEGIN

		DECLARE 	rowCount INT;
		SET 		rowCount = 0;
		
		 SELECT COUNT(*) INTO rowCount
			FROM Laptop L
			WHERE L.ComputerName =  ComputerName;
			
		IF rowCount > 0 THEN
			
		/*Delete  Laptop DATA*/
			DELETE FROM Laptop
			WHERE Laptop.ComputerName =  ComputerName;
			SET error_msg = 'Laptop Deleted!!';
		END IF;
		
		SET 		rowCount = 0;
		
		
		SELECT COUNT(*) INTO rowCount
			FROM Laptop L
			WHERE L.ComputerName =  ComputerName;
		 
		/*MAKE SURE THE Laptop IS SUCCESSFULLY Deleted*/
			
			IF rowCount = 0 THEN
				SET error_msg = 'Laptop Successfully Deleted';
			ELSE
				SET error_msg = 'Laptop Delete Error!!';
			END IF;
		
		
END$$     



DELIMITER $$

CREATE PROCEDURE LaptopUpdate(
	IN ComputerName VARCHAR(45),
	IN Size INT,
    IN ModelID VARCHAR(45),
    IN OS_ID VARCHAR(45),
	OUT error_msg VARCHAR (80) )
	BEGIN

		DECLARE 	rowCount INT;
		SET 		rowCount = 0;
		
		 SELECT COUNT(*) INTO rowCount
			FROM Laptop L
			WHERE L.ComputerName =  ComputerName;
			
		IF rowCount > 0 THEN
			UPDATE Laptop SET Size_Inch = Size
			WHERE Laptop.ComputerName = ComputerName;
            
            UPDATE Laptop SET ModelID = ModelID
			WHERE Laptop.ComputerName = ComputerName;
            
            UPDATE Laptop SET OS_ID = OS_ID
			WHERE Laptop.ComputerName = ComputerName;
            
            SET 		rowCount = 0;
			
			SELECT COUNT(*) INTO rowCount
				FROM Laptop L
				WHERE L.ComputerName =  ComputerName;
			 
			/*MAKE SURE THE Laptop IS SUCCESSFULLY Updated*/
				
				IF rowCount = 1 THEN
					SET error_msg = 'Laptop Successfully Updated';
				ELSE
					SET error_msg = 'Laptop Update Error!!';
				END IF;
		END IF;
		
		
		
		
END$$     




DELIMITER $$

CREATE PROCEDURE PCAdd(
	IN ComputerName VARCHAR(45),
	IN Memory_Gb INT,
    IN HardDrive_Gb INT,
    IN ModelID VARCHAR(45),
    IN OS_ID VARCHAR(45),
	OUT error_msg VARCHAR (80) )
	BEGIN

		DECLARE 	rowCount INT;
		SET 		rowCount = 0;
		
		 SELECT COUNT(*) INTO rowCount
			FROM PC P
			WHERE P.ComputerName =  ComputerName;
			
		IF rowCount > 0 THEN
			SET error_msg = 'PC Already Exists!!';
		ELSE
		/*ADD NEW PC DATA*/
			INSERT INTO PC (ComputerName, Memory_Gb, HardDrive_Gb, ModelID, OS_ID)
			VALUES (ComputerName, Memory_Gb, HardDrive_Gb, RetinaDisplay, ModelID, OS_ID);
			SET 		rowCount = 0;
		
		
			SELECT COUNT(*) INTO rowCount
				FROM PC P
				WHERE P.ComputerName =  ComputerName;
			 
			/*MAKE SURE THE PC IS SUCCESSFULLY ADDED*/
				
				IF rowCount = 1 THEN
					SET error_msg = 'New PC Successfully Added';
				ELSE
					SET error_msg = 'PC Insert Error!!';
				END IF;
		END IF;
		
		
		
		
END$$    





DELIMITER $$

CREATE PROCEDURE PCDelete(
	IN ComputerName VARCHAR(45),
	OUT error_msg VARCHAR (80) )
	BEGIN

		DECLARE 	rowCount INT;
		SET 		rowCount = 0;
		
		 SELECT COUNT(*) INTO rowCount
			FROM PC P
			WHERE P.ComputerName =  ComputerName;
			
		IF rowCount > 0 THEN
			
		/*Delete  PC DATA*/
			DELETE FROM PC 
			WHERE PC.ComputerName =  ComputerName;
			SET error_msg = 'PC Deleted!!';
		END IF;
		
		SET 		rowCount = 0;
		
		
		SELECT COUNT(*) INTO rowCount
			FROM PC P
			WHERE P.ComputerName =  ComputerName;
		 
		/*MAKE SURE THE PC IS SUCCESSFULLY Deleted*/
			
			IF rowCount = 0 THEN
				SET error_msg = 'PC Successfully Deleted';
			ELSE
				SET error_msg = 'PC Delete Error!!';
			END IF;
		
		
END$$    

DELIMITER $$

CREATE TRIGGER trg_updatePC BEFORE DELETE
	ON PC
    FOR EACH ROW 
    
    BEGIN
    
    DECLARE 	rowCount INT;
    
	SET 		rowCount = 0;
    
    SELECT COUNT(*) INTO rowCount
		FROM PC P
		WHERE P.ComputerName=  old.ComputerName;
    
    IF rowCount > 0 THEN
    UPDATE Account
		SET Account.ComputerName = 'Unspecified'
        WHERE Account.ComputerName = old.ComputerName;
        
	END IF;
END $$ 

DELIMITER ;

DELIMITER $$

CREATE PROCEDURE PCUpdate(
	IN ComputerName VARCHAR(45),
	IN Memory_Gb INT,
    IN HardDrive_Gb INT,
    IN ModelID VARCHAR(45),
    IN OS_ID VARCHAR(45),
	OUT error_msg VARCHAR (80) )
	BEGIN

		DECLARE 	rowCount INT;
		SET 		rowCount = 0;
		
		 SELECT COUNT(*) INTO rowCount
			FROM PC P
			WHERE P.ComputerName =  ComputerName;
			
		IF rowCount > 0 THEN
			
            UPDATE PC SET Memory_Gb = Memory_Gb
            WHERE PC.ComputerName = ComputerName;
           
            UPDATE PC SET HardDrive_Gb = HardDrive_Gb
            WHERE PC.ComputerName = ComputerName;
            
			UPDATE PC SET ModelID = ModelID
            WHERE PC.ComputerName = ComputerName;
            
			UPDATE PC SET OS_ID = OS_ID
            WHERE PC.ComputerName = ComputerName;
            
			SET 		rowCount = 0;
		
		
			SELECT COUNT(*) INTO rowCount
				FROM PC P
				WHERE P.ComputerName =  ComputerName;
			 
			/*MAKE SURE THE PC IS SUCCESSFULLY Updated*/
				
				IF rowCount = 1 THEN
					SET error_msg = 'PC Successfully Updated';
				ELSE
					SET error_msg = 'PC Update Error!!';
				END IF;
		END IF;
		
		
		
		
END$$    


DELIMITER $$

CREATE PROCEDURE ModelAdd(
	IN ModelID VARCHAR(45),
    IN ModelDescription VARCHAR(80),
    IN BrandID VARCHAR(45),
	OUT error_msg VARCHAR (80) )
	BEGIN

		DECLARE 	rowCount INT;
		SET 		rowCount = 0;
		
		 SELECT COUNT(*) INTO rowCount
			FROM Model M
			WHERE M.ModelID =  ModelID;
			
		IF rowCount > 0 THEN
			SET error_msg = 'Model Already Exists!!';
		ELSE
		/*ADD NEW Model DATA*/
			INSERT INTO Model (ModelId, ModelDescription, BrandID)
			VALUES (ModelId, ModelDescription, BrandID);
			SET 		rowCount = 0;
		
			SELECT COUNT(*) INTO rowCount
				FROM Model M
				WHERE M.ModelID =  ModelID;
			 
			/*MAKE SURE THE Model IS SUCCESSFULLY ADDED*/
				
				IF rowCount = 1 THEN
					SET error_msg = 'New Model Successfully Added';
				ELSE
					SET error_msg = 'Model Insert Error!!';
				END IF;
		END IF;
		
		
		
		
END$$   



DELIMITER $$

CREATE PROCEDURE ModelDelete(
	IN ModelID VARCHAR(45),
	OUT error_msg VARCHAR (80) )
	BEGIN

		DECLARE 	rowCount INT;
		SET 		rowCount = 0;
		
		 SELECT COUNT(*) INTO rowCount
			FROM Model M
			WHERE M.ModelID =  ModelID;
			
		IF rowCount > 0 THEN
		/*Delete  Model DATA*/
			DELETE FROM Model 
			WHERE Model.ModelID =  ModelID;
			SET error_msg = 'Model Deleted!!';
		END IF;
		
		SET 		rowCount = 0;
		
		
		SELECT COUNT(*) INTO rowCount
			FROM Model M
			WHERE M.ModelID =  ModelID;
		 
		/*MAKE SURE THE Model IS SUCCESSFULLY Deleted*/
			
			IF rowCount = 0 THEN
				SET error_msg = ' Model Successfully Deleted';
			ELSE
				SET error_msg = 'Model Delete Error!!';
			END IF;
		
		
END$$  

/*DROP TRIGGER  IF EXISTS trg_updateModel;*/

DELIMITER $$

CREATE TRIGGER trg_updateModel BEFORE DELETE
	ON Model
    FOR EACH ROW 
    
    BEGIN
    
    DECLARE 	rowCount INT;
    
	SET 		rowCount = 0;
    
    SELECT COUNT(*) INTO rowCount
		FROM Model M
		WHERE M.ModelID=  old.ModelID;
    
    IF rowCount > 0 THEN
    UPDATE Model_has_OperatingSystem
		SET Model_has_OperatingSystem.ModelID = 'Unspecified'
        WHERE Model_has_OperatingSystem.ModelID = old.ModelID;
     
	END IF;
END $$ 

DELIMITER ;


DELIMITER $$

CREATE PROCEDURE ModelUpdate(
	IN ModelID VARCHAR(45),
    IN ModelDescription VARCHAR(80),
    IN BrandID VARCHAR(45),
	OUT error_msg VARCHAR (80) )
	BEGIN

		DECLARE 	rowCount INT;
		SET 		rowCount = 0;
		
		 SELECT COUNT(*) INTO rowCount
			FROM Model M
			WHERE M.ModelID =  ModelID;
			
		IF rowCount > 0 THEN
			
            
			UPDATE Model SET ModelDescription = ModelDescription
            WHERE Model.ModelID = ModelID;
            
            UPDATE Model SET BrandID = BrandID
            WHERE Model.ModelID = ModelID;
            
			SET 		rowCount = 0;
		
			SELECT COUNT(*) INTO rowCount
				FROM Model M
				WHERE M.ModelID =  ModelID;
			 
			/*MAKE SURE THE Model IS SUCCESSFULLY Updated*/
				
				IF rowCount = 1 THEN
					SET error_msg = 'Model Successfully Updated';
				ELSE
					SET error_msg = 'Model Update Error!!';
				END IF;
		END IF;
		
		
		
		
END$$   



DELIMITER $$

CREATE PROCEDURE Model_has_OperatingSystemAdd(
	IN ModelID VARCHAR(45),
    IN OS_ID VARCHAR(80),
	OUT error_msg VARCHAR (80) )
	BEGIN

		DECLARE 	rowCount INT;
		SET 		rowCount = 0;
		
		 SELECT COUNT(*) INTO rowCount
			FROM Model_has_OperatingSystem M
			WHERE M.ModelID =  ModelID AND M.OS_ID = OS_ID;
			
		IF rowCount > 0 THEN
			SET error_msg = 'Model And OS Already Exists!!';
		ELSE
		/*ADD NEW Model_has_OperatingSystem DATA*/
			INSERT INTO Model_has_OperatingSystem (ModelId, OS_ID)
			VALUES (ModelId, OS_ID);
			SET 		rowCount = 0;
		
			SELECT COUNT(*) INTO rowCount
				FROM Model_has_OperatingSystem M
				WHERE M.ModelID =  ModelID AND M.OS_ID = OS_ID;
			 
			/*MAKE SURE THE Model_has_OperatingSystem IS SUCCESSFULLY ADDED*/
				
				IF rowCount = 1 THEN
					SET error_msg = 'New Model_has_OperatingSystem Successfully Added';
				ELSE
					SET error_msg = 'Model_has_OperatingSystem Insert Error!!';
				END IF;
		END IF;
		
		
		
		
END$$   

DELIMITER $$

CREATE PROCEDURE Model_has_OperatingSystemDelete(
	IN ModelID VARCHAR(45),
    IN OS_ID VARCHAR(45),
	OUT error_msg VARCHAR (80) )
	BEGIN

		DECLARE 	rowCount INT;
		SET 		rowCount = 0;
		
		 SELECT COUNT(*) INTO rowCount
			FROM Model_has_OperatingSystem M
			WHERE M.ModelID =  ModelID AND M.OS_ID = OS_ID;
			
		IF rowCount > 0 THEN
		/*Delete  Model_has_OperatingSystem DATA*/
			DELETE FROM Model_has_OperatingSystem 
			WHERE Model_has_OperatingSystem.ModelID =  ModelID AND Model_has_OperatingSystem.OS_ID = OS_ID;
			SET error_msg = 'Model_has_OperatingSystem Deleted!!';
		END IF;
		
		SET 		rowCount = 0;
		
		
		SELECT COUNT(*) INTO rowCount
			FROM Model_has_OperatingSystem M
			WHERE M.ModelID =  ModelID  AND M.OS_ID= OS_ID;
		 
		/*MAKE SURE THE Model_has_OperatingSystem IS SUCCESSFULLY Deleted*/
			
			IF rowCount = 0 THEN
				SET error_msg = ' Model_has_OperatingSystem Successfully Deleted';
			ELSE
				SET error_msg = 'Model_has_OperatingSystem Delete Error!!';
			END IF;
		
		
END$$  

/*DROP TRIGGER  IF EXISTS trg_updateModel_has_OperatingSystem;*/

DELIMITER $$

CREATE TRIGGER trg_updateModel_has_OperatingSystem BEFORE DELETE
	ON Model_has_OperatingSystem
    FOR EACH ROW 
    
    BEGIN
    
    DECLARE 	rowCount INT;
    
	SET 		rowCount = 0;
    
    SELECT COUNT(*) INTO rowCount
		FROM Model_has_OperatingSystem M
		WHERE M.ModelID=  old.ModelID AND M.OS_ID = OS_ID;
    
    IF rowCount > 0 THEN
    UPDATE PC
		SET PC.ModelID = 'Unspecified'
        WHERE PC.ModelID = old.ModelID;
     UPDATE PC
		SET PC.OS_ID = 'Unspecified'
        WHERE PC.OS_ID = old.OS_ID;    
     
     UPDATE IMac
		SET IMac.ModelID = 'Unspecified'
        WHERE IMac.ModelID = old.ModelID;
     UPDATE IMac
		SET IMac.OS_ID = 'Unspecified'
        WHERE IMac.OS_ID = old.OS_ID;
        
     UPDATE Laptop
		SET Laptop.ModelID = 'Unspecified'
        WHERE Laptop.ModelID = old.ModelID;
     UPDATE Laptop
		SET Laptop.OS_ID = 'Unspecified'
        WHERE Laptop.OS_ID = old.OS_ID;
        
	END IF;
END $$ 

DELIMITER ;

DELIMITER $$

CREATE PROCEDURE Model_has_OperatingSystemUpdate(
	IN ModelID VARCHAR(45),
    IN OS_ID VARCHAR(80),
	OUT error_msg VARCHAR (80) )
	BEGIN

		DECLARE 	rowCount INT;
		SET 		rowCount = 0;
		
		 SELECT COUNT(*) INTO rowCount
			FROM Model_has_OperatingSystem M
			WHERE M.ModelID =  ModelID AND M.OS_ID = OS_ID;
			
		IF rowCount > 0 THEN
			
            UPDATE Model_has_OperatingSystem SET OS_ID = OS_ID
            WHERE Model_has_OperatingSystem.ModelID = ModelID;
            
            UPDATE Model_has_OperatingSystem SET ModelID = ModelID
            WHERE Model_has_OperatingSystem.OS_ID = OS_ID;
            
			SET 		rowCount = 0;
		
			SELECT COUNT(*) INTO rowCount
				FROM Model_has_OperatingSystem M
				WHERE M.ModelID =  ModelID AND M.OS_ID = OS_ID;
			 
			/*MAKE SURE THE Model_has_OperatingSystem IS SUCCESSFULLY Updated*/
				
				IF rowCount = 1 THEN
					SET error_msg = 'Model_has_OperatingSystem Successfully Updated';
				ELSE
					SET error_msg = 'Model_has_OperatingSystem Update Error!!';
				END IF;
		END IF;
		
		
		
		
END$$   

DELIMITER $$

CREATE PROCEDURE OperatingSystemAdd(
	IN OS_ID VARCHAR(45),
    IN OS_Description VARCHAR(80),
	OUT error_msg VARCHAR (80) )
	BEGIN

		DECLARE 	rowCount INT;
		SET 		rowCount = 0;
		
		 SELECT COUNT(*) INTO rowCount
			FROM OperatingSystem O
			WHERE O.OS_ID =  OS_ID;
			
		IF rowCount > 0 THEN
			SET error_msg = 'OS Already Exists!!';
		ELSE
		/*ADD NEW OS DATA*/
			INSERT INTO OperatingSystem (OS_ID, OS_Description)
			VALUES (OS_ID, OS_Description);
			
            SET 		rowCount = 0;
		
			SELECT COUNT(*) INTO rowCount
				FROM OperatingSystem O
				WHERE O.OS_ID =  OS_ID;
			 
			/*MAKE SURE THE OS IS SUCCESSFULLY ADDED*/
				
				IF rowCount = 1 THEN
					SET error_msg = 'New OperatingSystem Successfully Added';
				ELSE
					SET error_msg = 'OperatingSystem Insert Error!!';
				END IF;
				
		END IF;
		
		
END$$  



DELIMITER $$

CREATE PROCEDURE OperatingSystemDelete(
	IN OS_ID VARCHAR(45),
	OUT error_msg VARCHAR (80) )
	BEGIN

		DECLARE 	rowCount INT;
		SET 		rowCount = 0;
		
		 SELECT COUNT(*) INTO rowCount
			FROM OperatingSystem O
			WHERE O.OS_ID =  OS_ID;
			
		IF rowCount > 0 THEN
		
		/*Delete  OS DATA*/
			DELETE FROM OperatingSystem 
            WHERE OperatingSystem.OS_ID =  OS_ID;
			SET 		rowCount = 0;
			
			SELECT COUNT(*) INTO rowCount
				FROM OperatingSystem O
				WHERE O.OS_ID =  OS_ID;
			 
			/*MAKE SURE THE OS IS SUCCESSFULLY Deleted*/
				
				IF rowCount = 0 THEN
					SET error_msg = 'OperatingSystem Successfully Deleted';
				ELSE
					SET error_msg = 'OperatingSystem Delete Error!!';
				END IF;
		END IF;
		
		
				
END$$  

/* DROP TRIGGER IF EXISTS trg_updateOperatingSystem;*/

DELIMITER $$

CREATE TRIGGER trg_updateOperatingSystem BEFORE DELETE
	ON OperatingSystem
    FOR EACH ROW 
    
    BEGIN
    
    DECLARE 	rowCount INT;
    
	SET 		rowCount = 0;
    
    SELECT COUNT(*) INTO rowCount
		FROM OperatingSystem O
		WHERE O.OS_ID=  old.OS_ID;
    
    IF rowCount > 0 THEN
    UPDATE Model_has_OperatingSystem
		SET Model_has_OperatingSystem.OS_ID = 'Unspecified'
        WHERE Model_has_OperatingSystem.OS_ID = old.OS_ID;
     
	END IF;
END $$ 

DELIMITER ;

DELIMITER $$

CREATE PROCEDURE OperatingSystemUpdate(
	IN OS_ID VARCHAR(45),
    IN OS_Description VARCHAR(80),
	OUT error_msg VARCHAR (80) )
	BEGIN

		DECLARE 	rowCount INT;
		SET 		rowCount = 0;
		
		 SELECT COUNT(*) INTO rowCount
			FROM OperatingSystem O
			WHERE O.OS_ID =  OS_ID;
			
		IF rowCount > 0 THEN
			UPDATE OperatingSystem SET OS_Description = OS_Description
            WHERE OperatingSystem.OS_ID = OS_ID;
			
            SET 		rowCount = 0;
		
			SELECT COUNT(*) INTO rowCount
				FROM OperatingSystem O
				WHERE O.OS_ID =  OS_ID;
			 
			/*MAKE SURE THE OS IS SUCCESSFULLY Updated*/
				
				IF rowCount = 1 THEN
					SET error_msg = 'OperatingSystem Successfully Updated';
				ELSE
					SET error_msg = 'OperatingSystem Update Error!!';
				END IF;
				
		END IF;
		
		
END$$  




DELIMITER $$

CREATE PROCEDURE Role_TypeAdd(
	IN RoleID VARCHAR(45),
    IN RoleDescription VARCHAR(80),
	OUT error_msg VARCHAR (80) )
	BEGIN

		DECLARE 	rowCount INT;
		SET 		rowCount = 0;
		
		 SELECT COUNT(*) INTO rowCount
			FROM Role_Type R
			WHERE R.RoleID =  RoleID;
			
		IF rowCount > 0 THEN
			SET error_msg = 'Role_Type Already Exists!!';
		ELSE
		/*ADD NEW Role_Type DATA*/
			INSERT INTO Role_Type (RoleID, RoleDescription)
			VALUES (RoleID, RoleDescription);
			SET 		rowCount = 0;
		
		
			SELECT COUNT(*) INTO rowCount
				FROM Role_Type R
				WHERE R.RoleID =  RoleID;
			 
			/*MAKE SURE THE Role_Type IS SUCCESSFULLY ADDED*/
				
				IF rowCount = 1 THEN
					SET error_msg = 'New Role_Type Successfully Added';
				ELSE
					SET error_msg = 'Role_Type Insert Error!!';
				END IF;
		END IF;
		
		
				
END$$    




DELIMITER $$

CREATE PROCEDURE Role_TypeDelete(
	IN RoleID VARCHAR(45),
	OUT error_msg VARCHAR (80) )
	BEGIN

		DECLARE 	rowCount INT;
		SET 		rowCount = 0;
		
		 SELECT COUNT(*) INTO rowCount
			FROM Role_Type R
			WHERE R.RoleID =  RoleID;
			
		IF rowCount > 0 THEN
		
		/*Delete  Role_Type DATA*/
			DELETE FROM Role_Type 
			WHERE Role_Type.RoleID =  RoleID;
			SET error_msg = 'Role Type Deleted!!';
		END IF;
		
		SET 		rowCount = 0;
		
		
		SELECT COUNT(*) INTO rowCount
			FROM Role_Type R
			WHERE R.RoleID =  RoleID;
		 
		/*MAKE SURE THE Role_Type IS SUCCESSFULLY Deleted*/
			
			IF rowCount = 0 THEN
				SET error_msg = 'New Role_Type Successfully Deleted';
			ELSE
				SET error_msg = 'Role_Type Delete Error!!';
			END IF;
				
END$$    

DELIMITER $$

CREATE TRIGGER trg_updateRole_Type BEFORE DELETE
	ON Role_Type
    FOR EACH ROW 
    
    BEGIN
    
    DECLARE 	rowCount INT;
    
	SET 		rowCount = 0;
    
    SELECT COUNT(*) INTO rowCount
		FROM Role_Type R
		WHERE R.RoleID=  old.RoleID;
    
    IF rowCount > 0 THEN
    UPDATE User
		SET User.RoleID = 'Unspecified'
        WHERE User.RoleID = old.RoleID;
        
	END IF;
END $$ 

DELIMITER ;

DELIMITER $$

CREATE PROCEDURE Role_TypeUpdate(
	IN RoleID VARCHAR(45),
    IN RoleDescription VARCHAR(80),
	OUT error_msg VARCHAR (80) )
	BEGIN

		DECLARE 	rowCount INT;
		SET 		rowCount = 0;
		
		 SELECT COUNT(*) INTO rowCount
			FROM Role_Type R
			WHERE R.RoleID =  RoleID;
			
		IF rowCount > 0 THEN
			UPDATE Role_Type SET RoleDescription = RoleDescription
            WHERE Role_Type.RoleID = RoleID;
            
			SET 		rowCount = 0;
		
		
			SELECT COUNT(*) INTO rowCount
				FROM Role_Type R
				WHERE R.RoleID =  RoleID;
			 
			/*MAKE SURE THE Role_Type IS SUCCESSFULLY Updated*/
				
				IF rowCount = 1 THEN
					SET error_msg = 'Role_Type Successfully Updated';
				ELSE
					SET error_msg = 'Role_Type Update Error!!';
				END IF;
		END IF;
		
		
				
END$$ 


DELIMITER $$

CREATE PROCEDURE UserAdd(
	IN UserID INT,
    IN FName VARCHAR(45),
    IN LName VARCHAR(45),
    IN ContactInfo VARCHAR(20),
    IN RoleID VARCHAR(45),
	OUT error_msg VARCHAR (80) )
	BEGIN

		DECLARE 	rowCount INT;
		SET 		rowCount = 0;

        
		
		 SELECT COUNT(*) INTO rowCount
			FROM User U
            WHERE U.UserID = UserID;
			
		IF rowCount > 0 THEN
			SET error_msg = 'User Already Exists!!';
		ELSE
        /*ADD NEW UserID DATA*/
				INSERT INTO User (UserID, FName, LName, ContactInfo, RoleID)
				VALUES (UserID, FName, LName, ContactInfo, RoleID);
				SET error_msg = 'User Added!!';
         SET 		rowCount = 0;
		
		
		SELECT COUNT(*) INTO rowCount
			FROM User U
			WHERE U.UserID =  UserID;
		 
		/*MAKE SURE THE User IS SUCCESSFULLY ADDED*/
			
			IF rowCount = 1 THEN
				SET error_msg = 'New User Successfully Added';
			ELSE
				SET error_msg = 'User Insert Error!!';
			END IF;
		       
		
		END IF;
		
		
		
END$$    


DELIMITER $$

CREATE PROCEDURE UserDelete(
	IN UserID INT,
	OUT error_msg VARCHAR (80) )
	BEGIN

		DECLARE 	rowCount INT;
		SET 		rowCount = 0;
		
		 SELECT COUNT(*) INTO rowCount
			FROM User U
			WHERE U.UserID =  UserID;
			
		IF rowCount > 0 THEN
			
		/*Delete  UserID DATA*/
			DELETE FROM User 
			WHERE User.UserID =  UserID;
			SET error_msg = 'User Deleted!!';
		END IF;
		
		SET 		rowCount = 0;
		
		
		SELECT COUNT(*) INTO rowCount
			FROM User U
			WHERE U.UserID =  UserID;
		 
		/*MAKE SURE THE User IS SUCCESSFULLY Deleted*/
			
			IF rowCount = 0 THEN
				SET error_msg = 'User Successfully Deleted';
			ELSE
				SET error_msg = 'User Delete Error!!';
			END IF;
		
		
END$$        

DELIMITER $$

CREATE TRIGGER trg_updateUser BEFORE DELETE
	ON User
    FOR EACH ROW 
    
    BEGIN
    
    DECLARE 	rowCount INT;
    
	SET 		rowCount = 0;
    
    SELECT COUNT(*) INTO rowCount
		FROM User U
		WHERE U.UserID=  old.UserID;
    
    IF rowCount > 0 THEN
    UPDATE Computer
		SET Computer.UserID = 0
        WHERE Computer.UserID = old.UserID;
        
	END IF;
END $$ 

DELIMITER ;

DELIMITER $$

CREATE PROCEDURE UserUpdate(
	IN UserID INT,
    IN FName VARCHAR(45),
    IN LName VARCHAR(45),
    IN ContactInfo VARCHAR(20),
    IN RoleID VARCHAR(45),
	OUT error_msg VARCHAR (80) )
	BEGIN

		DECLARE 	rowCount INT;
		SET 		rowCount = 0;

        
		
		 SELECT COUNT(*) INTO rowCount
			FROM User U
            WHERE U.UserID = UserID;
			
		IF rowCount > 0 THEN
			UPDATE User SET FName = FName
            WHERE User.UserID = UserID;
            
            UPDATE User SET LName = LName
            WHERE User.UserID = UserID;
                
            UPDATE User SET ContactInfo = ContactInfo
            WHERE User.UserID = UserID;
            
            UPDATE User SET RoleID = RoleID
            WHERE User.UserID = UserID;
            
			SET 		rowCount = 0;
			SELECT COUNT(*) INTO rowCount
				FROM User U
				WHERE U.UserID =  UserID;
			 
			/*MAKE SURE THE User IS SUCCESSFULLY Updated*/
				
				IF rowCount = 1 THEN
					SET error_msg = 'User Successfully Updated';
				ELSE
					SET error_msg = 'User Update Error!!';
				END IF;
				   
		
		END IF;
		
		
		
END$$                                                                                                            



DELIMITER ;


CREATE VIEW ComputerUserReport AS
SELECT		C.ComputerName, C.ComputerPassword, C.UserID, C.Room_Num, U.FName, U.LName, U.ContactInfo, U.RoleID,C.ComputerAssignedDate, C.ComputerReturnedDate, C.IsUpToDate
FROM		Computer C
LEFT JOIN User U	ON  C.UserID = U.UserID;


CREATE VIEW AddressComputerReport AS
SELECT		C.ComputerName, C.ComputerPassword, C.UserID, C.Room_Num, AD.BuildingName, AD.Department, AD.AddressDescription,C.ComputerAssignedDate, C.ComputerReturnedDate, C.IsUpToDate
FROM		Computer C
LEFT JOIN Address AD	ON C.Room_Num = AD.Room_Num;

CREATE VIEW AccountComputerPCUserReport AS
SELECT		C.ComputerName, C.ComputerPassword, C.UserID, C.Room_Num, U.FName, U.LName, U.ContactInfo, U.RoleID, A.AccountName, A.Pass_word, A.IsActive , C.ComputerAssignedDate, C.ComputerReturnedDate, C.IsUpToDate, P.HardDrive_Gb, P.Memory_Gb, P.ModelID, P.OS_ID
FROM		Computer C
LEFT JOIN User U	ON  C.UserID = U.UserID
LEFT JOIN PC P	ON  C.ComputerName = P.ComputerName
LEFT JOIN Account A		ON P.ComputerName = A.ComputerName;

CREATE VIEW BrandPCReport AS
SELECT		P.ComputerName, P.HardDrive_Gb, P.Memory_Gb, P.ModelID, P.OS_ID,B.BrandID, B.BrandDescription
FROM		PC P
LEFT JOIN Model M	ON  P.ModelID = M.ModelID
LEFT JOIN Brand B	ON  M.BrandID = B.BrandID;

CREATE VIEW BrandIMacReport AS
SELECT		I.ComputerName, I.RetinaDisplay, I.ModelID, I.OS_ID, B.BrandID, B.BrandDescription
FROM		IMac I
LEFT JOIN Model M	ON  I.ModelID = M.ModelID
LEFT JOIN Brand B	ON  M.BrandID = B.BrandID;

CREATE VIEW BrandLaptopReport AS
SELECT		L.ComputerName, L.Size_Inch, L.ModelID, L.OS_ID, B.BrandID, B.BrandDescription
FROM		Laptop L
LEFT JOIN Model M	ON  L.ModelID = M.ModelID
LEFT JOIN Brand B	ON  M.BrandID = B.BrandID;


CREATE VIEW PCUserReport AS
SELECT		P.ComputerName, P.HardDrive_Gb, P.Memory_Gb, P.ModelID, P.OS_ID, U.FName, U.LName, U.ContactInfo, U.RoleID
FROM		PC P
LEFT JOIN Computer C	ON  P.ComputerName = C.ComputerName
LEFT JOIN User U	ON  C.UserID = U.UserID;


CREATE VIEW IMacUserReport AS
SELECT		I.ComputerName, I.ModelID, I.OS_ID, U.FName, U.LName, U.ContactInfo, U.RoleID
FROM		IMac I
LEFT JOIN Computer C	ON  I.ComputerName = C.ComputerName
LEFT JOIN User U	ON  C.UserID = U.UserID;


CREATE VIEW LaptopUserReport AS
SELECT		L.ComputerName, L.Size_Inch, L.ModelID, L.OS_ID, U.FName, U.LName, U.ContactInfo, U.RoleID
FROM		Laptop L
LEFT JOIN Computer C	ON  L.ComputerName = C.ComputerName
LEFT JOIN User U	ON  C.UserID = U.UserID;

CREATE VIEW AddressComputerUserReport AS
SELECT		C.ComputerName, C.ComputerPassword, C.UserID, C.Room_Num, AD.BuildingName, AD.Department, AD.AddressDescription,U.FName, U.LName, U.ContactInfo, U.RoleID, C.ComputerAssignedDate, C.ComputerReturnedDate, C.IsUpToDate
FROM		Computer C
LEFT JOIN User U	ON  C.UserID = U.UserID
LEFT JOIN Address AD	ON C.Room_Num = AD.Room_Num;

CREATE VIEW AddressUserReport AS
SELECT		 U.UserID, U.RoleID,U.FName, U.LName, U.ContactInfo, AD.Room_Num, AD.BuildingName, AD.Department, AD.AddressDescription
FROM		User U
LEFT JOIN Computer C	ON  U.UserID = C.UserID
LEFT JOIN Address AD	ON C.Room_Num = AD.Room_Num;


CREATE VIEW AccountAddressComputerPCReport AS
SELECT		C.ComputerName, C.ComputerPassword, C.UserID, C.Room_Num, AD.BuildingName, AD.Department, AD.AddressDescription, A.AccountName, A.Pass_word,  A.IsActive ,C.ComputerAssignedDate, C.ComputerReturnedDate, C.IsUpToDate, P.HardDrive_Gb, P.Memory_Gb, P.ModelID, P.OS_ID
FROM		Computer C
LEFT JOIN Address AD	ON C.Room_Num = AD.Room_Num
LEFT JOIN PC P	ON  C.ComputerName = P.ComputerName
LEFT JOIN Account A		ON P.ComputerName = A.ComputerName;

CREATE VIEW AccountAddressComputerUserReport AS
SELECT		C.ComputerName, C.ComputerPassword, C.UserID, C.Room_Num, AD.BuildingName, AD.Department, AD.AddressDescription, A.AccountName, A.Pass_word,  A.IsActive ,C.ComputerAssignedDate, C.ComputerReturnedDate, C.IsUpToDate, U.ContactInfo, U.FName, U.LName, U.RoleID
FROM		Computer C
LEFT JOIN Address AD	ON C.Room_Num = AD.Room_Num
LEFT JOIN User U 		ON C.UserID = U.UserID
LEFT JOIN PC P	ON  C.ComputerName = P.ComputerName
LEFT JOIN Account A		ON P.ComputerName = A.ComputerName;

CREATE VIEW AccountAddressPCUserReport AS
SELECT		U.UserID, U.FName, U.LName, U.ContactInfo, U.RoleID ,AD.Room_Num, AD.BuildingName, AD.Department, AD.AddressDescription, A.AccountName, A.Pass_word,  A.IsActive , P.HardDrive_Gb, P.Memory_Gb, P.ModelID, P.OS_ID
FROM		User  U
LEFT JOIN Computer C	ON U.UserID = C.UserID
LEFT JOIN Address AD	ON C.Room_Num = AD.Room_Num
LEFT JOIN PC P			ON  C.ComputerName = P.ComputerName
LEFT JOIN Account A		ON P.ComputerName = A.ComputerName;

CREATE VIEW AccountPCUserReport AS
SELECT		U.UserID, U.FName, U.LName, U.ContactInfo, U.RoleID , A.AccountName, A.Pass_word,  A.IsActive , P.HardDrive_Gb, P.Memory_Gb, P.ModelID, P.OS_ID
FROM		User  U
LEFT JOIN Computer C	ON U.UserID = C.UserID
LEFT JOIN PC P			ON  C.ComputerName = P.ComputerName
LEFT JOIN Account A		ON P.ComputerName = A.ComputerName;



CREATE VIEW AddressComputerPCReport AS
SELECT		C.ComputerName, C.ComputerPassword, C.UserID, C.Room_Num, AD.BuildingName, AD.Department, AD.AddressDescription, C.ComputerAssignedDate, C.ComputerReturnedDate, C.IsUpToDate, P.HardDrive_Gb, P.Memory_Gb, P.ModelID, P.OS_ID
FROM		Computer C
LEFT JOIN Address AD	ON C.Room_Num = AD.Room_Num
LEFT JOIN PC P	ON  C.ComputerName = P.ComputerName;

CREATE VIEW AddressPCReport AS
SELECT		AD.Room_Num, AD.BuildingName, AD.Department, AD.AddressDescription, P.HardDrive_Gb, P.Memory_Gb, P.ModelID, P.OS_ID
FROM		PC P
LEFT JOIN Computer C	ON  P.ComputerName = C.ComputerName
LEFT JOIN Address AD	ON C.Room_Num = AD.Room_Num;


CREATE VIEW AddressComputerPCUserReport AS
SELECT		C.ComputerName, C.ComputerPassword, C.UserID, C.Room_Num, AD.BuildingName, AD.Department, AD.AddressDescription, C.ComputerAssignedDate, C.ComputerReturnedDate, C.IsUpToDate, P.HardDrive_Gb, P.Memory_Gb, P.ModelID, P.OS_ID,U.FName, U.LName, U.ContactInfo, U.RoleID
FROM		Computer C
LEFT JOIN Address AD	ON C.Room_Num = AD.Room_Num
LEFT JOIN PC P	ON  C.ComputerName = P.ComputerName
LEFT JOIN User U 	ON C.UserID = U.UserID;





CREATE VIEW AddressComputerIMacReport AS
SELECT		C.ComputerName, C.ComputerPassword, C.UserID, C.Room_Num, AD.BuildingName, AD.Department, AD.AddressDescription, C.ComputerAssignedDate, C.ComputerReturnedDate, C.IsUpToDate, I.RetinaDisplay, I.ModelID, I.OS_ID
FROM		Computer C
LEFT JOIN Address AD	ON C.Room_Num = AD.Room_Num
LEFT JOIN IMac I	ON  C.ComputerName = I.ComputerName;


CREATE VIEW AddressIMacReport AS
SELECT		AD.Room_Num, AD.BuildingName, AD.Department, AD.AddressDescription, I.RetinaDisplay, I.ModelID, I.OS_ID
FROM		IMac I
LEFT JOIN Computer C	ON  I.ComputerName = C.ComputerName
LEFT JOIN Address AD	ON C.Room_Num = AD.Room_Num;

CREATE VIEW AddressComputerIMacUserReport AS
SELECT		C.ComputerName, C.ComputerPassword, C.UserID, C.Room_Num, AD.BuildingName, AD.Department, AD.AddressDescription, C.ComputerAssignedDate, C.ComputerReturnedDate, C.IsUpToDate, I.RetinaDisplay, I.ModelID, I.OS_ID, U.FName, U.LName, U.ContactInfo, U.RoleID
FROM		Computer C
LEFT JOIN Address AD	ON C.Room_Num = AD.Room_Num
LEFT JOIN IMac I	ON  C.ComputerName = I.ComputerName
LEFT JOIN User U 	ON C.UserID = U.UserID;

CREATE VIEW AddressComputerLaptopReport AS
SELECT		C.ComputerName, C.ComputerPassword, C.UserID, C.Room_Num, AD.BuildingName, AD.Department, AD.AddressDescription, C.ComputerAssignedDate, C.ComputerReturnedDate, C.IsUpToDate, L.Size_Inch, L.ModelID, L.OS_ID
FROM		Computer C
LEFT JOIN Address AD	ON C.Room_Num = AD.Room_Num
LEFT JOIN Laptop L	ON  C.ComputerName = L.ComputerName;


CREATE VIEW AddressLaptopReport AS
SELECT		AD.Room_Num, AD.BuildingName, AD.Department, AD.AddressDescription, L.Size_Inch, L.ModelID, L.OS_ID
FROM		Laptop L
LEFT JOIN Computer C	ON  L.ComputerName = C.ComputerName
LEFT JOIN Address AD	ON C.Room_Num = AD.Room_Num;

CREATE VIEW AddressComputerLaptopUserReport AS
SELECT		C.ComputerName, C.ComputerPassword, C.UserID, C.Room_Num, AD.BuildingName, AD.Department, AD.AddressDescription, C.ComputerAssignedDate, C.ComputerReturnedDate, C.IsUpToDate, L.Size_Inch, L.ModelID, L.OS_ID, U.FName, U.LName, U.ContactInfo, U.RoleID
FROM		Computer C
LEFT JOIN Address AD	ON C.Room_Num = AD.Room_Num
LEFT JOIN Laptop L	ON  C.ComputerName = L.ComputerName
LEFT JOIN User U 	ON C.UserID = U.UserID;


CREATE VIEW ComputerPCReport AS
SELECT		C.ComputerName, C.ComputerPassword, C.UserID, C.Room_Num, C.ComputerAssignedDate, C.ComputerReturnedDate, C.IsUpToDate, P.HardDrive_Gb, P.Memory_Gb, P.ModelID, P.OS_ID
FROM		Computer C
LEFT JOIN PC P	ON  C.ComputerName = P.ComputerName;


CREATE VIEW ComputerIMacReport AS
SELECT		C.ComputerName, C.ComputerPassword, C.UserID, C.Room_Num, C.ComputerAssignedDate, C.ComputerReturnedDate, C.IsUpToDate, I.RetinaDisplay, I.ModelID, I.OS_ID
FROM		Computer C
LEFT JOIN IMac I	ON  C.ComputerName = I.ComputerName;

CREATE VIEW ComputerLaptopReport AS
SELECT		C.ComputerName, C.ComputerPassword, C.UserID, C.Room_Num, C.ComputerAssignedDate, C.ComputerReturnedDate, C.IsUpToDate, L.Size_Inch, L.ModelID, L.OS_ID
FROM		Computer C
LEFT JOIN Laptop L	ON  C.ComputerName = L.ComputerName;


CREATE VIEW Role_TypeUserReport AS
SELECT		U.FName, U.LName, U.ContactInfo, U.RoleID, R.RoleDescription
FROM		User U
LEFT JOIN Role_Type R	ON  U.RoleID = R.RoleID;


CREATE VIEW AccountComputerPCReport AS
SELECT		C.ComputerName, C.ComputerPassword, C.UserID, C.Room_Num, C.ComputerAssignedDate, C.ComputerReturnedDate, C.IsUpToDate, P.HardDrive_Gb, P.Memory_Gb, P.ModelID, P.OS_ID
FROM		Computer C
LEFT JOIN PC P	ON  C.ComputerName = P.ComputerName
LEFT JOIN Account A ON P.ComputerName = A.ComputerName;

CREATE VIEW AccountPCReport AS
SELECT		P.HardDrive_Gb, P.Memory_Gb, P.ModelID, P.OS_ID
FROM		PC P
LEFT JOIN Account A ON P.ComputerName = A.ComputerName;

CREATE VIEW BrandModelReport AS
SELECT		M.ModelID, M.ModelDescription, M.BrandID, B.BrandDescription
FROM		Model M
LEFT JOIN Brand B ON M.BrandID = B.BrandID;


CREATE VIEW IMacModelReport AS
SELECT		I.ComputerName, I.ModelID, I.OS_ID, I.RetinaDisplay, M.ModelDescription, M.BrandID
FROM		IMac I
LEFT JOIN Model M ON I.ModelID = M.ModelID;


CREATE VIEW ModelPCReport AS
SELECT		P.ComputerName, P.HardDrive_Gb, P.Memory_Gb, P.ModelID, M.ModelDescription, M.BrandID
FROM		PC P
LEFT JOIN Model M ON P.ModelID = M.ModelID;

CREATE VIEW OperatingSystemPCReport AS
SELECT		P.ComputerName, P.HardDrive_Gb, P.Memory_Gb, P.ModelID, O.OS_Description
FROM		PC P
LEFT JOIN OperatingSystem O ON P.OS_ID = O.OS_ID;

CREATE VIEW LaptopModelReport AS
SELECT		L.ComputerName, L.ModelID, L.OS_ID, L.Size_Inch, M.ModelDescription, M.BrandID
FROM		Laptop L
LEFT JOIN Model M ON L.ModelID = M.ModelID;

CREATE VIEW AccountAddressComputerPCUserReport AS
SELECT		C.ComputerName, C.ComputerPassword, C.UserID, C.Room_Num, AD.BuildingName, AD.Department, AD.AddressDescription, U.FName, U.LName, U.ContactInfo, U.RoleID, A.AccountName, A.Pass_word, A.IsActive, C.ComputerAssignedDate, C.ComputerReturnedDate, C.IsUpToDate, P.HardDrive_Gb, P.Memory_Gb, P.ModelID, P.OS_ID
FROM		Computer C
LEFT JOIN User U	ON  C.UserID = U.UserID
LEFT JOIN Address AD	ON C.Room_Num = AD.Room_Num
LEFT JOIN PC P 	ON C.ComputerName = P.ComputerName
LEFT JOIN Account A		ON P.ComputerName = A.ComputerName;


CREATE VIEW LaptopOperatingSystemReport AS
SELECT		L.ComputerName, L.ModelID, L.OS_ID, L.Size_Inch, O.OS_Description
FROM		Laptop L
LEFT JOIN OperatingSystem O ON L.OS_ID = O.OS_Description;


CREATE VIEW IMacOperatingSystemReport AS
SELECT		I.ComputerName, I.ModelID, I.OS_ID, I.RetinaDisplay, O.OS_Description
FROM		IMac I
LEFT JOIN OperatingSystem O ON I.OS_ID = O.OS_ID;

/*
CREATE VIEW AccountAddressBrandComputerIMacLaptopModelModel_has_OperatingSystemOperatingSystemPCRole_TypeUserReport AS
SELECT C.ComputerName, C.ComputerPassword, C.UserID, C.Room_Num, C.ComputerAssignedDate, C.ComputerReturnedDate, C.IsUpToDate, A.AccountName, A.Pass_word, A.IsActive, AD.BuildingName, AD.Department, AD.AddressDescription, I.ModelID, I.OS_ID, I.RetinaDisplay, L.ModelID, L.OS_ID, L.Size_Inch, M.ModelDescription, P.HardDrive_Gb, P.Memory_Gb, P.ModelID, B.BrandID, B.BrandDescription, U.FName, U.LName, U.ContactInfo, U.RoleID,R.RoleDescription
FROM 	Computer C 
LEFT JOIN User U	ON  C.UserID = U.UserID
LEFT JOIN Role_Type  R ON U.RoleID = R.RoleDescription
LEFT JOIN Address AD	ON C.Room_Num = AD.Room_Num
LEFT JOIN PC P 	ON C.ComputerName = P.ComputerName
LEFT JOIN Account A		ON P.ComputerName = A.ComputerName
LEFT JOIN IMac I	ON  C.ComputerName = I.ComputerName
LEFT JOIN Laptop L	ON  C.ComputerName = L.ComputerName
LEFT JOIN Model_has_OperatingSystem MH	ON  P.ModelID = MH.ModelID
LEFT JOIN Model_has_OperatingSystem MH	ON  I.ModelID = MH.ModelID
LEFT JOIN Model_has_OperatingSystem MH	ON  L.ModelID = MH.ModelID
LEFT JOIN OperatingSystem O	ON  MH.OS_ID = O.OS_ID
LEFT JOIN Model M	ON  MH.ModelID = M.ModelID
LEFT JOIN Brand B	ON  M.BrandID = B.BrandID;
*/