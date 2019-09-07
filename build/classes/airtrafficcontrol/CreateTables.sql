/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Author:  hashan
 * Created: Sep 7, 2019
 */
CREATE TABLE `air_traffic_control`.`times` ( `source` VARCHAR(5) NULL DEFAULT NULL , `destination` VARCHAR(5) NULL DEFAULT NULL , `flight_time` DOUBLE NULL DEFAULT NULL ) ENGINE = InnoDB;
