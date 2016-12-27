DELIMITER $$
create event out_dated_entries
on schedule every 1 minute
do begin
 
insert into `Inventory`.`Backlog` select * from `Inventory`.`ActiveInventory` where `Inventory`.`ActiveInventory`.`RunDate` < date(now());  
delete from `Inventory`.`ActiveInventory` where  `Inventory`.`ActiveInventory`.`RunDate` < date(now());



end $$
delimiter ;
