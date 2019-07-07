create table `cars` (
`id` int unsigned not null primary key auto_increment,
`brand` varchar(50) not null,
`model` varchar(50) not null,
`type` varchar(50) not null,
`year` int(4) not null,
`color` varchar(50) not null,
`mileage` int (10) not null,
`branch` varchar(50) not null,
`status` int (5) not null,
`price` int (10) not null
);