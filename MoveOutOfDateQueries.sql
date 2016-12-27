DELIMITER $$
create event out_dated_entries
on schedule every 1 minute
do begin
 
insert into `InventoryBackLog`.`Argus`  select * from Inventory.`Argus` where `run date` < date(now());  
delete from Inventory.`Argus` where  Inventory.`Argus`.`run date` < date(now());

insert into `InventoryBackLog`.`Bootheel`  select * from Inventory.`Bootheel` where `run date` < date(now());  
delete from Inventory.`Bootheel` where  Inventory.`Bootheel`.`run date` < date(now());

insert into `InventoryBackLog`.`Courier News`  select * from Inventory.`Courier News` where `run date` < date(now());  
delete from Inventory.`Courier News` where  Inventory.`Courier News`.`run date` < date(now());

insert into `InventoryBackLog`.`Delta Voice`  select * from Inventory.`Delta Voice` where `run date` < date(now());  
delete from Inventory.`Delta Voice` where  Inventory.`Delta Voice`.`run date` < date(now());

insert into `InventoryBackLog`.`Dyersburg News`  select * from Inventory.`Dyersburg News` where `run date` < date(now());  
delete from Inventory.`Dyersburg News` where  Inventory.`Dyersburg News`.`run date` < date(now());

insert into `InventoryBackLog`.`Kennett`  select * from Inventory.`Kennett` where `run date` < date(now());  
delete from Inventory.`Kennett` where  Inventory.`Kennett`.`run date` < date(now());

insert into `InventoryBackLog`.`Malden`  select * from Inventory.`Malden` where `run date` < date(now());  
delete from Inventory.`Malden` where  Inventory.`Malden`.`run date` < date(now());

insert into `InventoryBackLog`.`Osceola`  select * from Inventory.`Osceola` where `run date` < date(now());  
delete from Inventory.`Osceola` where  Inventory.`Osceola`.`run date` < date(now());

insert into `InventoryBackLog`.`Piggott`  select * from Inventory.`Piggott` where `run date` < date(now());  
delete from Inventory.`Piggott` where  Inventory.`Piggott`.`run date` < date(now());

insert into `InventoryBackLog`.`Portageville`  select * from Inventory.`Portageville` where `run date` < date(now());  
delete from Inventory.`Portageville` where  Inventory.`Portageville`.`run date` < date(now());

insert into `InventoryBackLog`.`State Gazette`  select * from Inventory.`State Gazette` where `run date` < date(now());  
delete from Inventory.`State Gazette` where  Inventory.`State Gazette`.`run date` < date(now());

insert into `InventoryBackLog`.`Steele`  select * from Inventory.`Steele` where `run date` < date(now());  
delete from Inventory.`Steele` where  Inventory.`Steele`.`run date` < date(now());

end $$
delimiter ;

