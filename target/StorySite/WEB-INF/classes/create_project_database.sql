
create tablespace sdproject_tabspace
datafile 'sdproject_tabspace.dat'
size 10M autoextend on;
  
  create temporary tablespace sdproject_tabspace_temp
  tempfile 'sdproject_tabspace_temp.dat'
  size 5M autoextend on;
  
  create user sdproject
  identified by sd
  default tablespace sdproject_tabspace
  temporary tablespace sdproject_tabspace_temp;
  
  grant create session to sdproject;
  grant create table to sdproject;
  grant unlimited tablespace to sdproject;
  grant all privileges to sdproject;