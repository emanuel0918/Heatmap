

/*------------BASE DE DATOS--------*/
drop database if exists  heatmap;
create database heatmap;
use heatmap;

/*-------Tablas------*/

drop table if exists usuario;
create table usuario (
idusuario int not null, 
nombre nvarchar (90) not null,
apellido nvarchar(90) not null,
usuario nvarchar(90) not null,
pasword nvarchar(90) not null,
correo nvarchar(90) not null,
fechaNac date not null,
sexo char(1) not null,
telefono nvarchar(120),
imagen nvarchar(90),
tipo char(1) not null,
idubicacion int,
confirmacion nvarchar(90) not null,
tokenpsw nvarchar(90),
cierre date,
antiguedad datetime  not null,
primary key(idusuario));

drop table if exists relusuarios;
create table relusuarios(
idrelusuarios int not null,
idUsuario1  int not null,
idUsuario2 int not null,
primary key (idrelusuarios),
foreign key(idUsuario1)
references usuario(idusuario),
foreign key(idUsuario2)
references usuario(idusuario));

drop table if exists conversacion;
create table conversacion (
idConversacion int not null,
título nvarchar (90),
primary key(idConversacion));

drop table if exists mensajes;
create table mensajes (
idMensajes int not null,
idConversacion int not null,
Mensaje nvarchar(90) not null,
fecha_hora timestamp not null,
remitente int not null,
receptor int not null,
primary key (idMensajes),
foreign key(idConversacion)
references conversacion(idConversacion));
 
drop table if exists menu;
create table menu(
idmenu int not null,
menu nvarchar(90) not null,
titulo nvarchar(90) not null,
ruta nvarchar(90) not null,
icono nvarchar(90) not null,
posicion decimal(2,1) not null,
primary key(idmenu));

drop table if exists relusrmenu; 
create table relusrmenu(
idrel int not null,
idmenu int not null,
tipo char(1) not null,
primary key (idrel));

drop table if exists ubicacion;
create table ubicacion(
idubicacion int not null,
coordenadax double not null,
coordenaday double not null,
primary key (idubicacion));

drop table if exists lugar;
create table lugar(
idlugar int not null,
titulo nvarchar(90) not null,
imagen nvarchar(90) not null,
idusuario int not null,
idubicacion int not null,
primary key (idlugar),
foreign key(idubicacion)
references ubicacion(idubicacion),
foreign key (idusuario)
references usuario(idusuario));

drop table if exists reportedelictivo;
create table reportedelictivo (
idreportedel int not null,
idubicacion int not null,
titulo nvarchar(90) not null,
descripcion text not null,
tipo nvarchar(90) not null,
fecha date not null,
hora time not null,
idusuario int not null,
primary key (idreportedel),
foreign key(idubicacion)
references ubicacion(idubicacion),
foreign key (idusuario)
references usuario(idusuario));

drop table if exists rutas;
create table rutas (
idruta int not null,
titulo nvarchar(90) not null,
puntoA nvarchar(90) not null,
puntoB nvarchar(90) not null,
idusuario int not null,
primary key (idruta),
foreign key (idusuario)
references usuario(idusuario));

drop table if exists anuncio;
create table anuncio (
idanuncio int not null,
titulo nvarchar(90) not null,
descripcion text not null,
imagen nvarchar(90) not null,
hr_ab nvarchar(5) not null,
hr_cerr nvarchar(5) not null,
calificacion int(1) not null,
idusuario int not null,
idubicacion int not null,
primary key (idanuncio),
foreign key(idubicacion)
references ubicacion(idubicacion),
foreign key (idusuario)
references usuario(idusuario));

drop table if exists categoria;
create table categoria (
idcat int not null,
titulo nvarchar(90) not null,
imagen nvarchar(90) not null,
color nvarchar(90) not null,
posicion int not null,
primary key (idcat));

drop table if exists relubicat;
create table relubicat(
idrel int not null,
idcat int not null,
idanuncio int not null,
primary key (idrel),
foreign key (idcat)
references categoria(idcat));

drop table if exists lenguaje;
create table lenguaje (
idleng int not null,
leng nvarchar(10) not null,
sLenguaje nvarchar(120) not null,
sCambiarLenguaje nvarchar(120) not null,
sBienvenido nvarchar(120) not null,
sContenidoIndex text not null,
sIniciarSesion nvarchar(120) not null,
sCerrarSesion nvarchar(120) not null,
sUsuario nvarchar(120) not null,
sPlaceHolderUsuario nvarchar(120) not null,
sContrasena nvarchar(120) not null,
sEntrar nvarchar(120) not null,
sOlvidarContrasena1 nvarchar(120) not null,
sOlvidarContrasena2 nvarchar(120) not null,
sBuscarCorreo nvarchar(120) not null,
sNuevaContrasena nvarchar(120) not null,
sConfirmarContrasena nvarchar(120) not null,
sNoTienesUnaCuentaEnHeatmap nvarchar(120) not null,
sRegistrateAqui nvarchar(120) not null,
sRegistrate nvarchar(120) not null,
sNombre nvarchar(120) not null,
sApellido nvarchar(120) not null,
sCorreo nvarchar(120) not null,
sSexo nvarchar(120) not null,
sMasculino nvarchar(120) not null,
sFemenino nvarchar(120) not null,
sTelefono nvarchar(120) not null,
sImagen nvarchar(120) not null,
sFechaNacimiento nvarchar(120) not null,
sUsuarioContrasenaIncorrecto nvarchar(120) not null,
sUsuarioAgregado nvarchar(120) not null,
sUsuarioModificado nvarchar(120) not null,
sUsuarioBorrado nvarchar(120) not null,
sUsuarioBorradoSi nvarchar(120) not null,
sUsuarioBorradoNo nvarchar(120) not null,
sUsuarioEncontrado nvarchar(120) not null,
sUsuarioYaExiste nvarchar(120) not null,
sUsuarioNoExiste nvarchar(120) not null,
sUsuarioNoValido nvarchar(120) not null,
sUsuarioConfirmadoSi nvarchar(120) not null,
sUsuarioConfirmadoNo nvarchar(120) not null,
sContrasenaCambiadaSi nvarchar(120) not null,
sContrasenaCambiadaNo nvarchar(120) not null,
sLugarAgregado nvarchar(120) not null,
sLugarModificado nvarchar(120) not null,
sLugarBorrado nvarchar(120) not null,
sLugarYaExiste nvarchar(120) not null,
sLugarNoExiste nvarchar(120) not null,
sReporteAgregado nvarchar(120) not null,
sReporteModificado nvarchar(120) not null,
sReporteBorrado nvarchar(120) not null,
sReporteYaExiste nvarchar(120) not null,
sReporteNoExiste nvarchar(120) not null,
sAnuncioAgregado nvarchar(120) not null,
sAnuncioModificado nvarchar(120) not null,
sAnuncioBorrado nvarchar(120) not null,
sAnuncioYaExiste nvarchar(120) not null,
sAnuncioNoExiste nvarchar(120) not null,
sAnuncioCategoriaAgregado nvarchar(120) not null,
sTelefonoYaExiste nvarchar(120) not null,
sTelefonoNoValido nvarchar(120) not null,
sCorreoYaExiste nvarchar(120) not null,
sCorreoNoValido nvarchar(120) not null,
sCoordenadaNoValida nvarchar(120) not null,
sFechaNoValida nvarchar(120) not null,
sHorarioNoValido nvarchar(120) not null,
sSexoNoValido nvarchar(120) not null,
sNoCamposVacios nvarchar(120) not null,
sError nvarchar(120) not null,
sMapa nvarchar(120) not null,
sMapaGeneral nvarchar(120) not null,
sMapaDelictivo nvarchar(120) not null,
sMapaLugares nvarchar(120) not null,
sMapaAnuncios nvarchar(120) not null,
sSinConversacion nvarchar(120) not null,
sSinResultadosDeBusqueda nvarchar(120) not null,
sNuevoContacto nvarchar(120) not null,
sAlta nvarchar(120) not null,
sAltaReporteDelictivo nvarchar(120) not null,
sAltaReporteDelictivoAqui nvarchar(120) not null,
sAltaLugar nvarchar(120) not null,
sAltaLugarAqui nvarchar(120) not null,
sAltaAnuncio nvarchar(120) not null,
sAltaAnuncioAqui nvarchar(120) not null,
sTitulo nvarchar(120) not null,
sLugar nvarchar(120) not null,
sTipoSiniestro nvarchar(120) not null,
sFechaAcontecimiento nvarchar(120) not null,
sHoraAcontecimiento nvarchar(120) not null,
sHorarioAbre nvarchar(120) not null,
sHorarioCierra nvarchar(120) not null,
sDescripcion nvarchar(120) not null,
sEstadisticas nvarchar(120) not null,
sAmigos nvarchar(120) not null,
sAmigo nvarchar(120) not null,
sUbicacion nvarchar(120) not null,
sConversacion nvarchar(120) not null,
sMensajes nvarchar(120) not null,
sEnviar nvarchar(120) not null,
sCompartirLugar nvarchar(120) not null,
sCalificarLugar nvarchar(120) not null,
sCalificacion nvarchar(120) not null,
sCategorias nvarchar(120) not null,
sCategoriaNoExiste nvarchar(120) not null,
sComida nvarchar(120) not null,
sEntretenimiento nvarchar(120) not null,
sServicios nvarchar(120) not null,
sLunes nvarchar(120) not null,
sMartes nvarchar(120) not null,
sMiercoles nvarchar(120) not null,
sJueves nvarchar(120) not null,
sViernes nvarchar(120) not null,
sSabado nvarchar(120) not null,
sDomingo nvarchar(120) not null,
sConfiguracion nvarchar(120) not null,
sMiPerfil nvarchar(120) not null,
sMembresia nvarchar(120) not null,
sAdministrarCuenta nvarchar(120) not null,
sErrorPagina nvarchar(120) not null,
sCorreoEnviadoRegistroAlert nvarchar(120) not null,
sCorreoEnviadoRegistroTitulo nvarchar(120) not null,
sCorreoEnviadoRegistroCuerpo text not null,
sCorreoEnviadoPaswordAlert nvarchar(120) not null,
sCorreoEnviadoPaswordTitulo nvarchar(120) not null,
sCorreoEnviadoPaswordCuerpo text not null,
sContrasenaNoValida nvarchar(120) not null,
sNombreLargo nvarchar(120) not null,
sContrasenaFormato text not null,
sCamposObligatorios nvarchar(120) not null,
sContrasenaIgualNo nvarchar(120) not null,
sDelitosPorMes nvarchar(120) not null,
sReporteMp nvarchar(120) not null,
sTipoDelito nvarchar(120) not null,
sSeleccionarCombobox nvarchar(120) not null,
sTipo1 nvarchar(120) not null,
sTipo2 nvarchar(120) not null,
sTipo3 nvarchar(120) not null,
sTipo4 nvarchar(120) not null,
sFecha nvarchar(120) not null,
sTipoNoValido nvarchar(120) not null,
sMostrarContrasena nvarchar(120) not null,
sCorreoCambioPasword nvarchar(120) not null,
sCorreoCambioPaswordBody text not null,
sCorreoCambioPaswordTitle nvarchar(120) not null,
sBuscar nvarchar(120) not null,
sCambioDeContrasenaCuerpo text not null,
sCambioDeContrasenaAlert nvarchar(120) not null,
sContrasenaCambiada nvarchar(120) not null,
sRutas nvarchar(120) not null,
sRutasEnHeatmap nvarchar(120) not null,
sRutasDesde nvarchar(120) not null,
sRutasHasta nvarchar(120) not null,
sRutasTipo nvarchar(120) not null,
sRutasMapa nvarchar(120) not null,
sRutasTipo1 nvarchar(120) not null,
sRutasTipo2 nvarchar(120) not null,
sRutasObtenerRuta nvarchar(120) not null,
sRutasPuntoNoValido nvarchar(120) not null,
sRutasPunto nvarchar(120) not null,
sBuscador nvarchar(120) not null,
sForo nvarchar(120) not null,
sConvCon nvarchar(120) not null,
sBuscarNo nvarchar(120) not null,
sAltaRuta nvarchar(120) not null,
sRutaYaExiste nvarchar(120) not null,
sRutaNoExiste nvarchar(120) not null,
primary key (idLeng));

------------------------------------------------------------------------------------
/*--------------------------FUNCTIONS------------------------------*/


drop function if exists f_IdUbicacion; 
delimiter //

create function f_IdUbicacion (p_coordenadax nvarchar(90), p_coordenaday nvarchar(90)) returns int
begin
declare idexiste int;
declare idsub int;
if((f_DecimalValido(p_coordenadax)=1) and(f_DecimalValido(p_coordenaday)=1))then
set idexiste = (select idubicacion from ubicacion where coordenadax=p_coordenadax
and coordenaday=p_coordenaday);
if (idexiste>0) then
return idexiste;
else
set idsub = (select max(idubicacion) from ubicacion);
if(idsub is null)then
set idsub=0;
end if;
insert into ubicacion values((idsub+1),p_coordenadax, p_coordenaday);
return (idsub+1);
end if;
end if;
end
//
delimiter ;


drop function if exists f_IdMenulist;
delimiter //
create function f_IdMenulist(p_menu nvarchar(90),
p_titulo nvarchar(90), p_ruta nvarchar(90),
p_icono nvarchar(90), p_posicion decimal(2,1)) returns int
begin
declare id int;
set id= (select idmenu from menu where menu=p_menu and 
titulo=p_titulo);
if(id is null)then
set id =  (select  max(idmenu)  from menu);
if(id is null)then
set id =0;
end if;
set id=id+1;
insert into menu values(id,p_menu,p_titulo,p_ruta,p_icono,p_posicion);
end if;
return id;
end
// delimiter ;


drop function if exists f_IdUsuario;
delimiter //
create function f_IdUsuario(p_usuario nvarchar(90)) returns int
begin
declare id int;
if (p_usuario is not null or (!(p_usuario='')))then
set id=(select idusuario from usuario where usuario=p_usuario);
if (id is null)then
set id=(select idusuario from usuario where correo=p_usuario);
if (id is null)then
set id=(select idusuario from usuario where telefono=p_usuario);
if (id is null)then
set id=0;
end if;
end if;
end if;
end if;
return id;
end
// delimiter ;


drop function if exists f_CorreoValido;
delimiter //
create function f_CorreoValido(p_correo nvarchar(90)) returns int
begin
declare valido int;
declare n int;
declare dbarroba int;
declare ind int;
set valido=1;
set n= length(p_correo);
if(n>=5)then
while(n>0)do
if(substr(p_correo,n,1)=' ')then
set valido=0;
set n=0;
end if;
set n=n-1;
end while;
if(valido=1)then
if(!(f_CaracterValido(substr(p_correo,1,1))=1 and 
(f_LetraValida(substr(p_correo,(length(p_correo)),1)))=1))then
set valido=0;
end if;
if(valido=1)then
set valido=0;
set n=2;
while(n<((length(p_correo))-1))do
if(substr(p_correo,n,1)='@')then
set valido=1;
set ind=n;
set n=((length(p_correo))-1);
end if;
set n=n+1;
end while;
if(valido=1)then
set n=length(p_correo);
while(n>ind)do
if(substr(p_correo,n,1)='@')then
set valido=0;
set n=0;
end if;
set n=n-1;
end while;
if(valido=1)then
set valido=0;
set n=length(p_correo);
set n=n-1;
while(n>(ind+1))do
if(substr(p_correo,n,1)='.')then
set valido=1;
set n=0;
end if;
set n=n-1;
end while;
end if;
end if;
end if;
end if;
else
set valido=0;
end if;
return valido;
end
// delimiter ;


drop function if exists f_UsuarioValido;
delimiter //
create function f_UsuarioValido(p_usuario nvarchar(90)) returns int
begin
declare caracteres nvarchar(90);
declare valido int;
declare n int;
declare i int;
set caracteres='´`*/+.{}[]^~¬°"#$%&()=¿?¡!><@ ';
set valido=0;
set i=(length(p_usuario));
set n=(length(caracteres));
if(i>4)then
set valido=1;
while(i>0)do
while(n>0)do
if((substr(p_usuario,i,1))=(substr(caracteres,n,1)))then
set valido=0;
set n=0;
set i=0;
end if;
set n=(n-1);
end while;
set n=(length(caracteres));
set i=(i-1);
end while;
end if;
return valido;
end
// delimiter ;


drop function if exists f_NumeroValido;
delimiter //
create function f_NumeroValido(p_numero nvarchar(90)) returns int
begin
declare valido int;
declare n int;
declare numeros nvarchar(90);
set numeros='0123456789';
if(length(p_numero)=1)then
set valido=0;
set n=length(numeros);
while (n>0)do
if(((substr(p_numero,1,1))=(substr(numeros,n,1))))then
set valido=1;
set n=0;
end if;
set n=n-1;
end while;
else
set valido=0;
end if;
return valido;
end
// delimiter ;


drop function if exists f_LetraValida;
delimiter //
create function f_LetraValida(p_caracter nvarchar(90)) returns int
begin
declare valido int;
declare n int;
declare caracteres nvarchar(90);
set caracteres='QWERTYUIOPASDFGHJKLZXCVBNMqwertyuiopasdfghjklzxcvbnm';
if(length(p_caracter)=1)then
set valido=0;
set n=length(caracteres);
while (n>0)do
if(((substr(p_caracter,1,1))=(substr(caracteres,n,1))))then
set valido=1;
set n=0;
end if;
set n=n-1;
end while;
else
set valido=0;
end if;
return valido;
end
// delimiter ;


drop function if exists f_CaracterValido;
delimiter //
create function f_CaracterValido(p_caracter nvarchar(90)) returns int
begin
declare valido int;
declare n int;
declare caracteres nvarchar(90);
set caracteres='0123456789QWERTYUIOPASDFGHJKLZXCVBNMqwertyuiopasdfghjklzxcvbnm';
if(length(p_caracter)=1)then
set valido=0;
set n=length(caracteres);
while (n>0)do
if(((substr(p_caracter,1,1))=(substr(caracteres,n,1))))then
set valido=1;
set n=0;
end if;
set n=n-1;
end while;
else
set valido=0;
end if;
return valido;
end
// delimiter ;


drop function if exists f_TelefonoValido;
delimiter //
create function f_TelefonoValido(p_telefono nvarchar(90)) returns int
begin
declare valido int;
declare n int;
set valido=0;
set n = (length(p_telefono));
if(n>=10 and n<15)then
while(n>0)do
if((f_NumeroValido(substr(p_telefono,n,1)))=1) then
set valido=1;
set n=0;
end if;
set n=(n-1);
end while;
if(valido=1)then
set n = (length(p_telefono));
while (n>0)do
if(!(substr(p_telefono,n,1)='+' or 
(f_NumeroValido(substr(p_telefono,n,1)))=1))then
set valido=0;
set n=0;
end if;
set n=(n-1);
end while;
end if;
else
set valido=0;
end if;
return valido;
end
// delimiter ;


drop function if exists f_DecimalValido;
delimiter //
create function f_DecimalValido(p_decimal nvarchar(90)) returns int
begin
declare valido int;
declare n int;
declare np int;
set valido=1;
set n=(length(p_decimal));
set np=(length(p_decimal));
while(n>1)do
if(!((substr(p_decimal,n,1)='.') or 
((f_NumeroValido(substr(p_decimal,n,1)))=1)))then
set valido=0;
set n=0;
end if;
set n=n-1;
end while;
if(valido=1)then
set n = 1;
while (n<(length(p_decimal)))do
if(substr(p_decimal,n,1)='.')then
set np=n;
set n=(length(p_decimal));
end if;
set n=n+1;
end while;
set n=np+1;
while (n<=(length(p_decimal)))do
if(substr(p_decimal,n,1)='.')then
set valido=0;
set n=((length(p_decimal))+1);
end if;
set n=n+1;
end while;
if(valido=1)then
if(substr(p_decimal,1,1)='-' or
substr(p_decimal,1,1)='+' or 
substr(p_decimal,1,1)='.' or 
(f_NumeroValido(substr(p_decimal,1,1)))=1)then
set valido=1;
else 
set valido=0;
end if;
end if;
end if;
return valido;
end
// delimiter ;


drop function if exists f_TelefonoValido;
delimiter //
create function f_TelefonoValido(p_telefono nvarchar(90)) returns int
begin
declare valido int;
declare n int;
declare np int;
set valido=1;
set n=(length(p_telefono));
set np=(length(p_telefono));
while(n>1)do
if(!((substr(p_telefono,n,1)='(') or
(substr(p_telefono,n,1)=')') or 
(substr(p_telefono,n,1)=' ') or 
((f_NumeroValido(substr(p_telefono,n,1)))=1)))then
set valido=0;
set n=0;
end if;
set n=n-1;
end while;
if(valido=1)then
if(substr(p_telefono,1,1)='+' or
(f_NumeroValido(substr(p_telefono,1,1)))=1)then
set valido=1;
else 
set valido=0;
end if;
end if;
return valido;
end
// delimiter ;



drop function if exists f_HorarioValido;
delimiter //
create function f_HorarioValido(p_horario nvarchar(90)) returns int
begin
declare valido int;
declare n int;
set n=length(p_horario);
set valido=0;
if (n=4 or n=5)then
set valido=0;
if(substr(p_horario,2,1)=':' and n=4
and f_NumeroValido(substr(p_horario,1,1))=1
and f_NumeroValido(substr(p_horario,3,1))=1
and f_NumeroValido(substr(p_horario,4,1))=1)then
set valido=1;
else
if(substr(p_horario,3,1)=':' and n=5
and f_NumeroValido(substr(p_horario,1,1))=1
and f_NumeroValido(substr(p_horario,2,1))=1
and f_NumeroValido(substr(p_horario,4,1))=1
and f_NumeroValido(substr(p_horario,5,1))=1)then
set valido=1;
end if;
end if;
end if;
return valido;
end
// delimiter ;


drop function if exists f_FechaValida;
delimiter //
create function f_FechaValida(p_fecha nvarchar(90)) returns int
begin
declare valido int;
declare n int;
set valido=0;
set n = (length(p_fecha));
if(n=10 and substr(p_fecha,5,1)='-'
and substr(p_fecha,8,1)='-'
and f_NumeroValido(substr(p_fecha,1,1))=1
and f_NumeroValido(substr(p_fecha,2,1))=1
and f_NumeroValido(substr(p_fecha,3,1))=1
and f_NumeroValido(substr(p_fecha,4,1))=1
and f_NumeroValido(substr(p_fecha,7,1))=1
and f_NumeroValido(substr(p_fecha,6,1))=1
and f_NumeroValido(substr(p_fecha,9,1))=1
and f_NumeroValido(substr(p_fecha,10,1))=1)then
set valido=1;
end if;
return valido;
end
// delimiter ;


drop function if exists f_Amigos;

delimiter //
create function f_Amigos( idUsu int,  idfriend int) returns nvarchar(90)
begin
declare suma int;
declare multi int;
declare conver nvarchar(90);
declare tipofinal int;
declare cuenta int;
declare cuenta2 int;

set cuenta=  (select idrelusuarios from relusuarios where idusuario1=idUsu and idusuario2=idfriend);
set cuenta2=  (select idrelusuarios from relusuarios where idusuario1=idfriend and idusuario2=idUsu);
set suma= cuenta+cuenta2;
set multi= cuenta*cuenta2;
set conver= concat(suma,'-',multi);
return conver;
end
// delimiter ;




/*--------------------------PROCEDIMIENTOS-------------------------*/

drop procedure if exists sp_AltaUsuario; 
delimiter //
/*------Validar que un correo no sea un usuario, ni que un usuario sea un correo-------*/
create procedure sp_AltaUsuario (in p_usuario nvarchar(90), 
in p_pasword nvarchar(90),in p_correo nvarchar(90),in p_nombre nvarchar(90), 
in p_apellido nvarchar(90), in p_fechaNac nvarchar(10),
in p_sexo nvarchar(90), in p_imagen nvarchar(120), in p_tel nvarchar(15),
in p_coorx nvarchar(90),in p_coory nvarchar(90),in p_codigo nvarchar(35))
begin
declare ids int;
declare v_tel nvarchar(90);
declare v_im nvarchar(120);
declare v_ub int;
if(!(p_usuario='' or p_pasword =''or p_correo='' or p_nombre='' or
p_apellido = '' or p_nombre = '' or p_apellido=''or
p_fechaNac='' or p_sexo=''  or p_codigo='' or
p_usuario is null or p_pasword is null or p_correo is null or p_nombre is null or
p_apellido is null or p_nombre is null or p_apellido is null or
p_fechaNac is null or p_sexo is null  or p_codigo is null ))then
if( f_UsuarioValido(p_usuario)=1)then
if( f_CorreoValido(p_correo)=1)then
if (p_sexo='M' or p_sexo='F') then
if(f_FechaValida(p_fechaNac)=1)then
if((f_IdUsuario(p_usuario)) = 0 ) then
if((f_IdUsuario(p_correo)) = 0 ) then
if((f_IdUsuario(p_tel)) = 0 ) then
if (((p_coorx='')and(p_coory='')) or ((p_coorx is null)and(p_coory is null)) or 
((f_DecimalValido(p_coorx)=1)and(f_DecimalValido(p_coory)=1))) then
if (p_tel='' or p_tel is null or f_TelefonoValido(p_tel)=1) then
if (p_tel='' or p_tel is null ) then
set v_tel='';
else
set v_tel=p_tel;
end if;
if(p_imagen='' or p_imagen is null)then
if(p_sexo='M')then
set v_im='http://i.imgur.com/6cY0syl.png';
else
set v_im='http://i.imgur.com/BzNupgP.png';
end if;
else
set v_im=p_imagen;
end if;
if((((p_coorx='')and(p_coory='')) or ((p_coorx is null)and(p_coory is null))))then
set v_ub=null;
else
set v_ub=f_IdUbicacion(p_coorx,p_coory);
end if;
set ids=(select max(idusuario) from usuario);
if(ids is null)then
set ids=0;
end if;
insert into usuario  values((ids+1), p_nombre, p_apellido,
p_usuario, p_pasword, p_correo,p_fechaNac, p_sexo, v_tel,v_im ,'E',v_ub,p_codigo,null, null, now());
select 1 as 'idStatus', 'sUsuarioAgregado' 
as 'mensaje', u.* from usuario as u where idusuario=(ids+1);
else
select 0 as 'idStatus', 'sTelefonoNoValido' as 'mensaje' ;
end if;
else
select 0 as 'idStatus', 'sCoordenadaNoValida' as 'mensaje' ;
end if;
else
select 0 as 'idStatus', 'sTelefonoYaExiste' as 'mensaje' ;
end if;
else
select 0 as 'idStatus', 'sCorreoYaExiste' as 'mensaje' ;
end if;
else
select 0 as 'idStatus', 'sUsuarioYaExiste' as 'mensaje' ;
end if;
else
select 0 as 'idStatus', 'sFechaNoValida' as 'mensaje';
end if;
else
select 0 as 'idStatus', 'sSexoNoValido' as 'mensaje';
end if;
else
select 0 as 'idStatus', 'sCorreoNoValido' as 'mensaje';
end if;
else
select 0 as 'idStatus', 'sUsuarioNoValido' as 'mensaje';
end if;
else
select 0 as 'idStatus', 'sNoCamposVacios' as 'mensaje';
end if;
end
//
delimiter ;

drop procedure if exists sp_Chat;

delimiter //
create procedure sp_Chat(in idUsu int, in idfriend int) 
begin
declare suma int;
declare multi int;
declare conver nvarchar(90);
declare tipofinal int;
declare cuenta int;
declare cuenta2 int;

set cuenta=  (select idrelusuarios from relusuarios where idusuario1=idUsu and idusuario2=idfriend);
set cuenta2=  (select idrelusuarios from relusuarios where idusuario1=idfriend and idusuario2=idUsu);
set suma= cuenta+cuenta2;
set multi= cuenta*cuenta2;
set conver= concat(suma,'-',multi);
select suma as 'sum' , multi as 'multi';
end
// delimiter ;


drop procedure if exists sp_ConsultaUsuario; 
delimiter //
create procedure sp_ConsultaUsuario (in p_usuario nvarchar(90))
begin
declare id int;
set id =f_IdUsuario(p_usuario);
if id > 0 then
select 1 as 'idStatus', 'sUsuarioEncontrado' as 'mensaje', u.* from usuario as u where idusuario=id;
else
select 0 as 'idStatus', 'sUsuarioNoExiste' as 'mensaje';
end if;
end
//
delimiter ;


/*-----Validar el nusuario antes de modificar el usuario-----*/
drop procedure if exists sp_ModificaUsuario; 
delimiter //
create procedure sp_ModificaUsuario (in p_usuario nvarchar(90),
in p_nusuario nvarchar(90),
 in p_nombre nvarchar(90), in p_apellido nvarchar(90), 
in p_fechaNac nvarchar(10), in p_sexo char(1), in p_imagen nvarchar(120), 
in p_tel nvarchar(15),in p_coorx nvarchar(90),in p_coory nvarchar(90))
begin
declare id int;
declare v_tel nvarchar(90);
declare v_im nvarchar(120);
declare v_ub int;
if(!(p_usuario='' or p_nusuario=''
or p_nombre='' or
p_apellido = '' or p_nombre = '' or p_apellido=''or
p_fechaNac='' or p_sexo='' or
p_usuario is null or p_nusuario is null
or p_nombre is null or
p_apellido is null or p_nombre is null or p_apellido is null or
p_fechaNac is null or p_sexo is null))then
if( f_UsuarioValido(p_nusuario)=1)then
if (p_sexo='M' or p_sexo='F') then
if(f_FechaValida(p_fechaNac)=1)then
set id = (select idusuario from usuario where usuario=p_usuario);
if (id > 0) then
if((f_IdUsuario(p_nusuario))=0 or (p_usuario=p_nusuario))then
if((f_IdUsuario(p_tel))=0 or f_IdUsuario(p_usuario)=f_IdUsuario(p_tel))then
if (((p_coorx='')and(p_coory='')) or
((p_coorx is null)and(p_coory is null)) or 
((f_DecimalValido(p_coorx)=1)and(f_DecimalValido(p_coory)=1))) then
if (p_tel='' or p_tel is null or f_TelefonoValido(p_tel)=1) then
if (p_tel='' or p_tel is null ) then
set v_tel='';
else
set v_tel=p_tel;
end if;
if(p_imagen='' or p_imagen is null)then
if(p_sexo='M')then
set v_im='http://i.imgur.com/6cY0syl.png';
else
set v_im='http://i.imgur.com/BzNupgP.png';
end if;
else
set v_im=p_imagen;
end if;
if((((p_coorx='')and(p_coory='')) or ((p_coorx is null)and(p_coory is null))))then
set v_ub=null;
else
set v_ub=f_IdUbicacion(p_coorx,p_coory);
end if;
update usuario set usuario=p_nusuario, nombre=p_nombre, apellido=p_apellido,
fechaNac=p_fechaNac, sexo=p_sexo, imagen=v_im, telefono=v_tel, idubicacion=v_ub where idusuario=id;
select 1 as 'idStatus', 'sUsuarioModificado';
else
select 0 as 'idStatus', 'sTelefonoNoValido' as 'mensaje' ;
end if;
else
select 0 as 'idStatus', 'sCoordenadaNoValida' as 'mensaje' ;
end if;
else
select 0 as 'idStatus', 'sTelefonoYaExiste' as 'mensaje';
end if;
else
select 0 as 'idStatus', 'sUsuarioYaExiste' as 'mensaje';
end if;
else
select 0 as 'idStatus','sUsuarioNoExiste' as 'mensaje';
end if;
else
select 0 as 'idStatus', 'sFechaNoValida' as 'mensaje';
end if;
else
select 0 as 'idStatus', 'sSexoNoValido' as 'mensaje';
end if;
else
select 0 as 'idStatus', 'sUsuarioNoValido' as 'mensaje';
end if;
else
select 0 as 'idStatus','sNoCamposVacios' as 'mensaje';
end if;
end
//
delimiter ;


drop procedure if exists sp_ConfirmacionBajaUsuario; 
delimiter //
create procedure sp_ConfirmacionBajaUsuario (in p_usuario nvarchar(90), 
in p_pasword nvarchar(90))
begin
declare id int;
set id=(select idusuario from usuario where usuario=p_usuario and pasword=p_pasword);
if id > 0 then
select 1 as 'idStatus','sUsuarioBorradoSi' as 'mensaje',
u.* from usuario as u where usuario=p_usuario;
end if;
end
//
delimiter ;


drop procedure if exists sp_BajaUsuarios; 
delimiter //
create procedure sp_BajaUsuarios ()
begin
delete r.* from amigo  as r inner join usuario as u on r.idusuario1=u.idusuario
where (curdate()>=date_add(u.cierre,interval 60 day)
or ((!(u.confirmacion='s')) and (curdate()>=date_add(u.cierre,interval 6 day))));
delete r.* from amigo  as r inner join usuario as u on r.idusuario2=u.idusuario
where (curdate()>=date_add(u.cierre,interval 60 day)
or ((!(u.confirmacion='s')) and (curdate()>=date_add(u.cierre,interval 6 day))));
delete r.* from reportedelictivo as r inner join usuario as u on r.idusuario=u.idusuario
where (curdate()>=date_add(u.cierre,interval 60 day)
or ((!(u.confirmacion='s')) and (curdate()>=date_add(u.cierre,interval 6 day))));
delete l.* from lugar as l inner join usuario as u on l.idusuario=u.idusuario
where (curdate()>=date_add(u.cierre,interval 60 day)
or ((!(u.confirmacion='s')) and (curdate()>=date_add(u.cierre,interval 6 day))));
delete an.* from anuncio as an inner join usuario as u on an.idusuario=u.idusuario
where (curdate()>=date_add(u.cierre,interval 60 day)
or ((!(u.confirmacion='s')) and (curdate()>=date_add(u.cierre,interval 6 day))));
delete u.* from usuario as u 
where (curdate()>=date_add(u.cierre,interval 60 day)
or ((!(u.confirmacion='s')) and (curdate()>=date_add(u.cierre,interval 6 day))));
end
//
delimiter ;


drop procedure if exists sp_CierraCuenta; 

delimiter //
create procedure sp_CierraCuenta (in p_usuario nvarchar(90))
begin
declare id int;
set id = f_IdUsuario(p_usuario);
if id > 0 then
update usuario set cierre=curdate() where id = idusuario;
select  1 as 'idStatus','sUsuarioBorrado' as 'mensaje';
else
select 0 as 'idStatus','sUsuarioNoExiste' as 'mensaje';
end if;
end
//
delimiter ;


drop procedure if exists sp_SolicitudModificaPaswordUsuario; 
delimiter //
create procedure sp_SolicitudModificaPaswordUsuario (
in p_token nvarchar(35), in p_correo nvarchar(90))
begin
update usuario set tokenpsw=p_token where correo=p_correo
and idusuario=f_IdUsuario(p_correo);
end
//
delimiter ;


drop procedure if exists sp_ModificaPaswordUsuario;
delimiter //
create procedure sp_ModificaPaswordUsuario (in p_token nvarchar(35), in p_psw nvarchar(90))
begin
declare id int;
set id = (select idusuario from usuario where tokenpsw=p_token);
if (id is not null) then
update usuario set pasword=p_psw where tokenpsw=p_token and idusuario=id;
select 1 as 'idStatus', 'sContrasenaCambiadaSi' as 'mensaje';
else
select 0 as 'idStatus', 'sError' as 'mensaje';
end if;
end
//
delimiter ;


drop procedure if exists sp_ConfirmacionUsuario; 
delimiter //

create procedure sp_ConfirmacionUsuario (in p_codigo nvarchar(200))
begin
declare id int;
if(!(p_codigo='s'))then
set id = (select idusuario from usuario where confirmacion=p_codigo);
if(id is not null)then
update usuario set confirmacion='s' where idusuario=id;
select 1 as 'idStatus', 'sUsuarioConfirmadoSi' as 'mensaje';
else
select 0 as 'idStatus', 'sError' as 'mensaje';
end if;
else
select 0 as 'idStatus', 'sError' as 'mensaje';
end if;
end
//
delimiter ;


drop procedure if exists sp_LogIn;
delimiter //

create procedure sp_LogIn (in p_usuario nvarchar(90), in p_pasword nvarchar(90))
begin
declare id int;
declare confirm nvarchar(90);
set id = f_IdUsuario(p_usuario);
if(id>0)then
set confirm = (select confirmacion from usuario where idusuario=id and pasword=p_pasword);
if (confirm='s') then
select 1 as 'idStatus', usuario,nombre,apellido,'sBienvenido' as 'mensaje'
from usuario where idusuario=id;
update usuario set cierre=null where idusuario=id;
else
select 0 as 'idStatus', 'sUsuarioContrasenaIncorrecto' as 'mensaje';
end if;
else
select 0 as 'idStatus', 'sUsuarioContrasenaIncorrecto' as 'mensaje';
end if;
end
//
delimiter ;


drop procedure if exists sp_ValidaUsuario;
delimiter //

create procedure sp_ValidaUsuario (in p_usuario nvarchar(90))
begin
declare idusr int;
set idusr=(select idusr from usuario where usuario=p_usuario);
if(idusr is null) then
select 1 as'idStatus','sUsuarioNoExiste' as 'mensaje';
else
select 0 as'idStatus','sUsuarioYaExiste' as 'mensaje';
end if;
end
//
delimiter ;


drop procedure if exists sp_AltaLugar; 
delimiter //

create procedure sp_AltaLugar (in p_usuario nvarchar(90), in coorx nvarchar(90),
in coory nvarchar(90), in p_titulo nvarchar(90), in p_imagen nvarchar(90))
begin
declare idusr int;
declare idub int;
declare ids int;
declare v_im nvarchar(90);
if((!(p_titulo='' or coorx='' or coory='' or
coorx is null or coory is null or p_titulo is null)))then
set idusr=f_IdUsuario(p_usuario);
if idusr > 0 then
set idusr = (select idlugar from lugar where idusuario=(f_IdUsuario(p_usuario))
and titulo=p_titulo);
if(idusr is null)then
if((f_DecimalValido(coorx)=1)and (f_DecimalValido(coory)=1))then
set idub = f_IdUbicacion(coorx,coory);
set ids = (select max(idlugar) from lugar);
if(ids is null)then
set ids=0;
end if;
if(p_imagen='' or p_imagen is null)then
set v_im='http://i.imgur.com/HmUMIGC.png';
else
set v_im=p_imagen;
end if;
insert into lugar values((ids+1),p_titulo,v_im,f_IdUsuario(p_usuario),idub);
select 1 as 'idStatus', 'sLugarAgregado' as 'mensaje';
else
select 0 as 'idStatus', 'sCoordenadaNoValida' as 'mensaje';
end if;
else
select 0 as 'idStatus', 'sLugarYaExiste' as 'mensaje';
end if;
else
select 0 as 'idStatus', 'sUsuarioNoExiste' as 'mensaje';
end if;
else
select 0 as 'idStatus', 'sNoCamposVacios' as 'mensaje';
end if;
end
//
delimiter ;


drop procedure if exists sp_ModificaLugar; 
delimiter //

create procedure sp_ModificaLugar (in p_usuario nvarchar(90),
in p_titulo nvarchar(90), in coorx nvarchar(90),
in coory nvarchar(90), in p_ntitulo nvarchar(90), in p_imagen nvarchar(90))
begin
declare idusr int;
declare idub int;
declare id int;
declare v_im nvarchar(90);
if(idusr>0 or (!(p_ntitulo='' or coorx='' or coory='' or
coorx is null or coory is null or p_ntitulo is null)))then
set idusr=f_IdUsuario(p_usuario);
if(idusr>0)then
set id = (select idlugar from lugar where titulo=p_titulo and idusuario=idusr);
if (id is not null)then
if((f_DecimalValido(coorx)=1)and (f_DecimalValido(coory)=1))then
set id = (select idlugar from lugar where titulo=p_ntitulo and idusuario=f_IdUsuario(p_usuario));
if(id is null or p_titulo=p_ntitulo)then
if(p_imagen='' or p_imagen is null)then
set v_im='http://i.imgur.com/HmUMIGC.png';
else
set v_im=p_imagen;
end if;
set id = (select idlugar from lugar where titulo=p_titulo and idusuario=f_IdUsuario(p_usuario));
update lugar set titulo=p_ntitulo, imagen=v_im,
idubicacion=f_IdUbicacion(coorx,coory) where idlugar=id;
select 1 as 'idStatus', 'sLugarModificado' as 'mensaje';
else
select 0 as 'idStatus', 'sLugarYaExiste' as 'mensaje';
end if;
else
select 0 as 'idStatus', 'sCoordenadaNoValida' as 'mensaje';
end if;
else
select 0 as 'idStatus', 'sLugarNoExiste' as 'mensaje';
end if;
else
select 0 as 'idStatus', 'sUsuarioNoExiste' as 'mensaje';
end if;
else
select 0 as 'idStatus', 'sNoCamposVacios' as 'mensaje';
end if;
end
//
delimiter ;


drop procedure if exists sp_BajaLugar; 

delimiter //
create procedure sp_BajaLugar (in p_usuario nvarchar(90), in p_titulo nvarchar(90))
begin
declare id int;
set id = f_IdUsuario(p_usuario);
if(id>0)then
set id = (select idlugar from lugar where titulo=p_titulo and idusuario=id);
if (id is not null)then
delete from lugar  where idlugar=id;
select 1 as 'idStatus', 'sLugarBorrado' as 'mensaje';
else
select 0 as 'idStatus', 'sLugarNoExiste' as 'mensaje';
end if;
else
select 0 as 'idStatus', 'sUsuarioNoExiste' as 'mensaje';
end if;
end
//
delimiter ;


drop procedure if exists sp_LugaresUsuario; 

delimiter //
create procedure sp_LugaresUsuario (in p_usuario nvarchar(90))
begin
select l.titulo, l.imagen,ub.coordenadax,ub.coordenaday from lugar as l inner join ubicacion as ub on 
l.idubicacion=ub.idubicacion inner join usuario as u
on u.idusuario=l.idusuario where u.idusuario=f_IdUsuario(p_usuario);
end
//
delimiter ;


drop procedure if exists sp_AltaAnuncio; 
delimiter //

create procedure sp_AltaAnuncio (in p_usuario nvarchar(90), in coorx nvarchar(90),
in coory nvarchar(90), in p_titulo nvarchar(90), in p_descripcion text,
in p_imagen nvarchar(90), in p_hr_ab nvarchar(90), in p_hr_cerr nvarchar(90))
begin
declare idusr int;
declare ids int;
declare v_im nvarchar(90);
if((!(p_titulo='' or coorx='' or coory='' or
p_descripcion='' or p_hr_ab='' or p_hr_cerr='' or
coorx is null or coory is null or p_titulo is null or
p_descripcion is null or p_hr_ab is null or p_hr_cerr is null)))then
set idusr=f_IdUsuario(p_usuario);
if (idusr >0) then
set idusr = (select idusuario from usuario where idusuario=idusr and tipo='A');
if (idusr is not null) then
set idusr = (select idanuncio from anuncio where idusuario=(f_IdUsuario(p_usuario))
and titulo=p_titulo);
if(idusr is null)then
if((f_DecimalValido(coorx)=1)and (f_DecimalValido(coory)=1))then
if((f_HorarioValido(p_hr_ab)=1)and (f_HorarioValido(p_hr_cerr)=1))then
set ids = (select max(idanuncio) from anuncio);
if(ids is null)then
set ids=0;
end if;
if(p_imagen = '' or p_imagen is null)then
set v_im='http://i.imgur.com/oF4JEUX.png';
else
set v_im=p_imagen;
insert into anuncio values((ids+1),p_titulo,p_descripcion,v_im,p_hr_ab,
p_hr_cerr,3,f_IdUsuario(p_usuario),f_IdUbicacion(coorx,coory));
end if;
select 1 as 'idStatus', 'sAnuncioAgregado' as 'mensaje';
else
select 0 as 'idStatus', 'sHorarioNoValido' as 'mensaje';
end if;
else
select 0 as 'idStatus', 'sCoordenadaNoValida' as 'mensaje';
end if;
else
select 0 as 'idStatus', 'sAnuncioYaExiste' as 'mensaje';
end if;
else
select 0 as 'idStatus', 'sUsuarioProNo' as 'mensaje';
end if;
else
select 0 as 'idStatus', 'sUsuarioNoExiste' as 'mensaje';
end if;
else
select 0 as 'idStatus', 'sNoCamposVacios' as 'mensaje';
end if;
end
//
delimiter ;


drop procedure if exists sp_ModificaAnuncio; 
delimiter //

create procedure sp_ModificaAnuncio (in p_usuario nvarchar(90), 
in coorx nvarchar(90),in coory nvarchar(90), 
in p_titulo nvarchar(90), in p_ntitulo nvarchar(90), 
in p_descripcion text,in p_imagen nvarchar(90), 
in p_hr_ab nvarchar(90), in p_hr_cerr nvarchar(90))
begin
declare idusr int;
declare ids int;
declare v_im nvarchar(90);
set idusr=f_IdUsuario(p_usuario);
set ids=(select count(*) from anuncio where titulo=p_titulo and idusuario=idusr);
if(ids>0)then
if((!(p_ntitulo='' or coorx='' or coory='' or
p_descripcion='' or p_hr_ab='' or p_hr_cerr='' or
coorx is null or coory is null or p_ntitulo is null or
p_descripcion is null or p_hr_ab is null or p_hr_cerr is null)))then
if(f_IdUsuario(p_usuario)>0)then
set idusr = (select idusuario from usuario where idusuario=idusr and tipo='A');
if (idusr is not null) then
set idusr = (select idanuncio from anuncio where idusuario=(f_IdUsuario(p_usuario))
and titulo=p_ntitulo);
if(idusr is null or p_titulo=p_ntitulo)then
if((f_DecimalValido(coorx)=1)and (f_DecimalValido(coory)=1))then
if((f_HorarioValido(p_hr_ab)=1)and (f_HorarioValido(p_hr_cerr)=1))then
if(p_imagen = '' or p_imagen is null)then
set v_im='http://i.imgur.com/oF4JEUX.png';
else
set v_im=p_imagen;
end if;
set ids = (select idanuncio from anuncio where titulo=p_titulo 
and idusuario=f_IdUsuario(p_usuario));
update anuncio set titulo=p_ntitulo, descripcion=p_descripcion,
imagen=v_im, hr_ab=p_hr_ab, hr_cerr=p_hr_cerr where idanuncio=ids;
select 1 as 'idStatus', 'sAnuncioModificado' as 'mensaje';
else
select 0 as 'idStatus', 'sHorarioNoValido' as 'mensaje';
end if;
else
select 0 as 'idStatus', 'sCoordenadaNoValida' as 'mensaje';
end if;
else
select 0 as 'idStatus', 'sAnuncioYaExiste' as 'mensaje';
end if;
else
select 0 as 'idStatus', 'sUsuarioProNo' as 'mensaje';
end if;
else
select 0 as 'idStatus', 'sUsuarioNoExiste' as 'mensaje';
end if;
else
select 0 as 'idStatus', 'sNoCamposVacios' as 'mensaje';
end if;
else
select 0 as 'idStatus', 'sAnuncioNoExiste' as 'mensaje';
end if;
end
//
delimiter ;


drop procedure if exists sp_ConsultaAnuncios; 

delimiter //
create procedure sp_ConsultaAnuncios (in p_anuncio nvarchar(90))
begin
select an.titulo as 'anuncio', an.descripcion, an.imagen,
concat(an.hr_ab,'-',an.hr_cerr) as 'horario', cat.titulo,
cat.imagen as 'logo',cat.color as 'categoria' 
from relubicat as r inner join anuncio as an on an.idanuncio=r.idanuncio
inner join categoria as cat on cat.idcat=r.idcat
where cat.titulo=p_anuncio order by cat.posicion;
end
//
delimiter ;


drop procedure if exists sp_BajaAnuncio; 

delimiter //
create procedure sp_BajaAnuncio (in p_usuario nvarchar(90), in p_titulo nvarchar(90))
begin
declare id int;
set id=(select idanuncio from anuncio 
where idusuario=f_IdUsuario(p_usuario) and titulo=p_titulo);
if(id is null)then
select 0 as 'idStatus', 'sUsuarioNoEncontrado' as 'mensaje';
else
select 1 as 'idStatus', 'sAnuncioBorrado' as 'mensaje';
delete from anuncio where idanuncio=id;
delete from relubicat where idanuncio=id;
end if; 
end
//
delimiter ;


drop procedure if exists sp_AltaReporteDelictivo; 

delimiter //
create procedure sp_AltaReporteDelictivo (in p_usuario nvarchar(90),
in p_titulo nvarchar(90),in p_descripcion text,
in p_coordenadax nvarchar(90),in p_coordenaday nvarchar(90),
 in p_tipo nvarchar(90))
begin
declare idusr int;
declare idsr int;
set idusr = f_IdUsuario(p_usuario);
if(idusr>0)then
set idusr =(select idusuario from reportedelictivo where titulo=p_titulo and idusuario=idusr);
if(idusr is null)then
set idusr = f_IdUsuario(p_usuario);
if((!(p_titulo='' or p_descripcion='' or p_tipo='' or
p_coordenadax='' or p_coordenaday='' or
p_titulo is null or p_descripcion is null or p_tipo is null or
p_coordenadax is null or p_coordenaday is null)))then
if((f_DecimalValido(p_coordenadax)=1)and (f_DecimalValido(p_coordenaday)=1))then
set idsr = (select max(idreportedel) from reportedelictivo);
if(idsr is null)then
set idsr=0;
end if;
insert into reportedelictivo values((idsr+1),
(f_IdUbicacion(p_coordenadax,p_coordenaday)),p_titulo,
p_descripcion,p_tipo,curdate(),curtime(),idusr);
select 1 as 'idStatus', 'sReporteAgregado' as 'mensaje';
else
select 0 as 'idStatus', 'sCoordenadaNoValida' as 'mensaje';
end if;
else
select 0 as 'idStatus', 'sNoCamposVacios' as 'mensaje';
end if;
else
select 0 as 'idStatus', 'sReporteYaExiste' as 'mensaje';
end if;
else
select 0 as 'idStatus', 'sUsuarioNoExiste' as 'mensaje';
end if;
end
//
delimiter ;


drop procedure if exists sp_ModificaReporteDelictivo; 

delimiter //
create procedure sp_ModificaReporteDelictivo (in p_usuario nvarchar(90),
in p_titulo nvarchar(90),in p_ntitulo nvarchar(90),
in p_descripcion text,in p_fecha nvarchar(90),
in p_hora nvarchar(90),in p_coordenadax nvarchar(90),
in p_coordenaday nvarchar(90), in p_tipo nvarchar(90))
begin
declare idusr int;
declare id int;
set idusr = f_IdUsuario(p_usuario);
set id=(select idreportedel from reportedelictivo where titulo=p_titulo and idusuario=idusr);
if(id is not null)then
if((!(p_ntitulo='' or p_descripcion='' or p_tipo='' or
p_fecha='' or p_hora='' or p_coordenadax='' or p_coordenaday='' or
p_ntitulo is null or p_descripcion is null or p_tipo is null or
p_fecha is null or p_hora is null or p_coordenadax is null 
or p_coordenaday is null)))then
if((f_DecimalValido(p_coordenadax)=1)and (f_DecimalValido(p_coordenaday)=1))then
if(f_HorarioValido(p_hora)=1)then
if(f_FechaValida(p_fecha)=1)then
set id=(select idreportedel from reportedelictivo where titulo=p_ntitulo
and idusuario=f_IdUsuario(p_usuario));
if(p_titulo=p_ntitulo or id is null)then
set id=(select idreportedel from reportedelictivo where titulo=p_titulo
and idusuario=f_IdUsuario(p_usuario));
update reportedelictivo set idubicacion=(f_IdUbicacion(p_coordenadax,p_coordenaday)),
titulo=p_ntitulo, descripcion=p_descripcion,tipo=p_tipo,fecha=p_fecha,
hora=p_hora where idreportedel=id;
select 1 as 'idStatus', 'sReporteModificado' as 'mensaje';
else
select 0 as 'idStatus', 'sReporteYaExiste' as 'mensaje';
end if;
else
select 0 as 'idStatus', 'sFechaNoValida' as 'mensaje';
end if;
else
select 0 as 'idStatus', 'Hora no Valida' as 'mensaje';
end if;
else
select 0 as 'idStatus', 'sCoordenadaNoValida' as 'mensaje';
end if;
else
select 0 as 'idStatus', 'sNoCamposVacios' as 'mensaje';
end if;
else
select 0 as 'idStatus', 'sReporteNoExiste' as 'mensaje';
end if;
end
//
delimiter ;


drop procedure if exists sp_ConsultaReportesPorTipo;

delimiter //
create procedure sp_ConsultaReportesPorTipo (in p_tipo nvarchar(90))
begin
select rep.titulo,rep.fecha,
day(rep.fecha) as 'dia', ub.coordenadax ,
ub.coordenaday from reportedelictivo as rep
inner join ubicacion as ub on ub.idubicacion=rep.idubicacion
where rep.tipo=p_tipo
order by TIMESTAMPDIFF(day,rep.fecha,curdate());
end
//
delimiter ;


drop procedure if exists sp_ConsultaReportes;

delimiter //
create procedure sp_ConsultaReportes()
begin
select rep.titulo,rep.fecha,
day(rep.fecha) as 'dia', ub.coordenadax ,
ub.coordenaday from reportedelictivo as rep
inner join ubicacion as ub on ub.idubicacion=rep.idubicacion
order by TIMESTAMPDIFF(day,rep.fecha,curdate());
end
//
delimiter ;


drop procedure if exists sp_BajaReporteDelictivo; 

delimiter //
create procedure sp_BajaReporteDelictivo (in p_usuario nvarchar(90),
in p_titulo nvarchar(90))
begin
declare id int;
set id=(select idreportedel from reportedelictivo where 
titulo=p_titulo and idusuario=f_IdUsuario(p_usuario));
if(id is not null)then
delete from reportedelictivo where idreportedel=id;
select 1 as 'idStatus', 'sReporteBorrado' as 'mensaje';
else
select 0 as 'idStatus', 'sReporteNoExiste' as 'mensaje';
end if;
end
//
delimiter ;



drop procedure if exists sp_BorraReportesDelictivos; 

delimiter //
create procedure sp_BorraReportesDelictivos ()
begin
delete r.* from reportedelictivo as r where
((curdate()>=date_add(r.fecha,interval 60 day)));
end
//
delimiter ;


drop procedure if exists sp_AltaRuta; 

delimiter //
create procedure sp_AltaRuta (in p_usuario nvarchar(90),
in p_titulo nvarchar(90),in p_puntoA text,
in p_puntoB nvarchar(90))
begin
declare idusr int;
declare idsr int;
set idusr = f_IdUsuario(p_usuario);
if(idusr>0)then
set idusr =(select idusuario from rutas where titulo=p_titulo and idusuario=idusr);
if(idusr is null)then
set idusr = f_IdUsuario(p_usuario);
if((!(p_titulo='' or p_puntoA='' or p_puntoB='' 
or p_titulo is null or p_puntoA is null or p_puntoB is null)))then
set idsr = (select max(idruta) from rutas);
if(idsr is null)then
set idsr=0;
end if;
insert into rutas values((idsr+1),
p_titulo,p_puntoA,p_puntoB,idusr);
select 1 as 'idStatus', 'sAltaRuta' as 'mensaje';
else
select 0 as 'idStatus', 'sNoCamposVacios' as 'mensaje';
end if;
else
select 0 as 'idStatus', 'sRutaYaExiste' as 'mensaje';
end if;
else
select 0 as 'idStatus', 'sUsuarioNoExiste' as 'mensaje';
end if;
end
//
delimiter ;

drop procedure if exists sp_ConsultaRutas;
delimiter //
create procedure sp_ConsultaRutas(in p_usuario nvarchar(90))
begin
select r.titulo, r.puntoA, r.puntoB
from rutas as r where r.idusuario=f_IdUsuario(p_usuario);
end
// delimiter ;

drop procedure if exists sp_BajaRuta;
delimiter //
create procedure sp_BajaRuta(in p_usuario nvarchar(90),
in p_titulo nvarchar(90))
begin
declare id int;
set id=(select idruta from rutas where idusuario=(f_IdUsuario(p_usuario)) and titulo=p_titulo);
if(id is not null)then
delete from rutas where idruta=id;
select 1 as 'idStatus';
else
select 0 as 'idStatus', 'sRutaNoExiste' as 'mensaje';
end if;
end
// delimiter ;


drop procedure if exists sp_AltaCategoria; 
delimiter //
create procedure sp_AltaCategoria(in p_titulo nvarchar(90),
in p_imagen nvarchar(90), in p_color nvarchar(90), in p_posicion int)
begin
declare ids int;
declare v_im nvarchar(90);
declare v_color nvarchar(90);
if(!(p_titulo='' or p_titulo is null)) then
set ids=(select idcat from categoria where titulo=p_titulo);
if(ids is null)then
set ids=(select max(idcat) from categoria);
if(ids is null) then
set ids=0;
end if;
if(p_imagen = '' or p_imagen is null)then
set v_im='http://i.imgur.com/TpmKaLa.png';
else
set v_im=p_imagen;
end if;
if(p_color = '' or p_color is null)then
set v_color='#808080';
else
set v_color=p_color;
end if;
insert into categoria values((ids+1),p_titulo,v_im,v_color,p_posicion);
select 1 as 'idStatus', 'Categoria agregada satisfactoriamente' as 'mensaje';
else
select 0 as 'idStatus', 'La categoria ya existe' as 'mensaje';
end if;
else
select 0 as 'idStatus', 'No dejes campos vacios' as 'mensaje';
end if;
end
// delimiter ;


drop procedure if exists sp_BajaCategoria; 
delimiter //
create procedure sp_BajaCategoria(in p_titulo nvarchar(90))
begin
declare id int;
set id=(select idcat from categoria where titulo=p_titulo);
if(id is not null)then
delete from categoria where idcat=id;
delete from relubicat where idcat=id;
select 1 as 'idStatus', 'Categoria borrada' as 'mensaje';
else
select 0 as 'idStatus', 'La categoria no existe' as 'mensaje';
end if;
end
// delimiter ;


drop procedure if exists sp_AltaAnuncioCategoria; 
delimiter //
create procedure sp_AltaAnuncioCategoria(in p_usuario nvarchar(90),
in p_titulo nvarchar(90), in p_cat nvarchar(90))
begin
declare id int;
declare ids int;
declare v_idcat int;
set id=(select idanuncio from anuncio where titulo=p_titulo
and idusuario=f_IdUsuario(p_usuario));
if(id is not null)then
set v_idcat=(select idcat from categoria where titulo=p_cat);
if(v_idcat is not null)then
set ids=(select idrel from relubicat where idcat=v_idcat and idanuncio=id);
if(ids is null)then
set ids=(select max(idrel) from relubicat);
if(ids is null)then
set ids=0;
end if;
insert into relubicat values((ids+1),v_idcat,id);
select 1 as 'idStatus', 'sAnuncioCategoriaAgregado' as 'mensaje';
end if;
else
select 0 as 'idStatus', 'sCategoriaNoExiste' as 'mensaje';
end if;
else
select 0 as 'idStatus', 'sAnuncioNoExiste' as 'mensaje';
end if;
end
// delimiter ;


drop procedure if exists sp_BajaAnuncioCategoria; 
delimiter //
create procedure sp_BajaAnuncioCategoria(in p_usuario nvarchar(90),
in p_titulo nvarchar(90), in p_cat nvarchar(90))
begin
declare id int;
declare v_idcat int;
set id=(select idanuncio from anuncio where titulo=p_titulo
and idusuario=f_IdUsario(p_usuario));
if(id is not null)then
set v_idcat=(select idcat from categoria where titulo=p_cat);
if(v_idcat is not null)then
set id =(select idrel from relubicat where idanuncio =id and idcat=v_idcat);
if(id is not null)then
delete from relubicat where idrel=id;
select 1 as 'idStatus';
end if;
end if;
else
select 0 as 'idStatus', 'sAnuncioNoExiste' as 'mensaje';
end if;
end
// delimiter ;


drop procedure if exists sp_Menu; 
delimiter //
create procedure sp_Menu(in p_usuario nvarchar(90), in p_menu nvarchar(90))
begin
declare v_tipo char(1);
set v_tipo=(select tipo from usuario where idusuario=f_IdUsuario(p_usuario));
select  m.titulo,m.ruta,m.icono,m.posicion from relusrmenu as r inner join menu as m on
 m.idmenu=r.idmenu where r.tipo=v_tipo and m.menu=p_menu order by m.posicion;
end
// delimiter ;


drop procedure if exists sp_AltaMenu;

delimiter //
create procedure sp_AltaMenu(in p_menu nvarchar(90),
in p_posicion decimal(2,1),in p_tipo char(1),
p_icono nvarchar(90), in p_titulo nvarchar(90),
 in p_ruta nvarchar(90))
begin
declare id int;
declare ids int;
set id=f_IdMenulist(p_menu,p_titulo,p_ruta,p_icono,p_posicion);
set ids=(select idrel from relusrmenu where idmenu=id and tipo=p_tipo);
if(ids is null)then
set ids= (select max(idrel) from relusrmenu);
if(ids is null)then
set ids=0;
end if;
insert into relusrmenu values((ids+1),id,p_tipo);
else
select 'El menu ya existe' as 'mensaje';
end if;
end
// delimiter ;


drop procedure if exists sp_BajaMenu;

delimiter //
create procedure sp_BajaMenu(in p_titulo nvarchar(90))
begin
declare id int;
set id= (select idmenu from menu where titulo=p_titulo);
delete from menu where idmenu=id;
delete from relusrmenu where idmenu = id;
end
// delimiter ;



drop procedure if exists sp_NuevoMensaje;

delimiter //
create procedure sp_NuevoMensaje(in emisor nvarchar(90),
 in recep nvarchar(90), in men nvarchar(90))
begin
declare existe int;
declare tamanioM int;
declare tamanioC int;
declare emi int;
declare rece int;
declare conv int;
declare titulcon nvarchar(90);
set emi= (select idusuario from usuario where usuario=emisor);
set rece= (select idusuario from usuario where usuario=recep);
set titulcon = f_Amigos(emi,rece);
set existe = (select count(*) idConversacion from conversacion 
where título=titulcon);
if (existe=0) then
set tamanioC =(select max(idConversacion) from conversacion);
if(tamanioC is null)then
set tamanioC=0;
end if;
insert into conversacion (idConversacion, título) 
values ((tamanioC+1), titulcon);
set conv=(tamanioC+1);
end if;
if (existe>0)
then
set conv=(select idConversacion from conversacion
 where título=titulcon);
end if;
set tamanioM=(select max(idMensajes) from mensajes);
if(tamanioM is null)then
set tamanioM=0;
end if;
insert into mensajes (idMensajes, idConversacion, Mensaje, fecha_hora, remitente, receptor)
values((tamanioM+1), conv, men, now(),emi, rece );
end
// delimiter ;


drop procedure if exists sp_ConsultaMensajes;

delimiter //
create procedure sp_ConsultaMensajes(in emisa nvarchar(90) , in recepi nvarchar(90))
begin 
declare existe int;
declare conv int;
declare msj nvarchar(90);
set existe=(select count(*) from conversacion where título=(concat(emisa,'-',recepi))
or título=(concat(recepi,'-',emisa)));
if existe=0 then
set msj=concat('Aún no tienes conversaciones con ',recepi,' envíale un mensaje ahora');
select msj as msj, '1' as a;
else
set msj='conversación encontrada';
set conv=(select idConversacion from Conversacion where
título=(concat(emisa,'-',recepi)) or título=(concat(recepi,'-',emisa)));
select *, '2'as a from vw_MensajesConversacion
where idConversacion = conv order by fecha_hora asc;
end if;
end
// delimiter ;


drop procedure if exists sp_Buscador;

delimiter //
create procedure sp_Buscador (in entrada nvarchar(90))
begin 
declare oc int; 
set entrada = replace(entrada,' ','');
set oc= (select count(*) from usuario where usuario=entrada or (concat(nombre,apellido)
 like concat('%',entrada,'%') ));
if oc = 0 then
select 'No se han encontrado resultados' as 'msj';
else
select nombre, apellido, imagen, sexo, '1' as 'msj', idUsuario from usuario 
where usuario=entrada or (concat(nombre,apellido) like concat('%',entrada,'%') );
end if;
end
// delimiter ;

drop procedure if exists sp_Quecon;
delimiter uwu
create procedure sp_Quecon(in id1 int ,in id2  int)
BEGIN
declare usu1 varchar(45);
declare usu2 varchar (45);
declare conbi1 varchar (180);
declare conbi2 varchar (180);
declare existe nvarchar(90);
declare coco nvarchar(90);
set usu1 = (select usuario from usuario where idUsuario=id1);
set usu2 = (select usuario from usuario where idUsuario=id2);
set coco = f_Amigos(id1,id2);
set existe=(select count(*)  from Conversacion where título=coco);

select coco as 'idConversacion';







END
uwu

delimiter ;
select * from relusuarios;
drop procedure if exists sp_Amigos;

delimiter //
create procedure sp_Amigos(in opc int,in idUsu int, in idfriend int)
begin
declare maxim int;
declare existe int;
declare tiporel1 nvarchar(10);
declare tiporel2 nvarchar(10);
declare tipofinal int;
declare cuenta int;
declare cuenta2 int;
Case opc
when 0 then
select * from (select idusuario1,idUsuario2 from relusuarios where idUsuario1=idUsu) v 
inner join (select * from relusuarios where idUsuario2=idUsu) vv on vv.idUsuario1=v.idUsuario2
inner join usuario on v.idUsuario2=idusuario;
when 1 then
set existe=(select count(idrelusuarios) from relusuarios where idUsuario1=idUsu and idUsuario2=idfriend);
set maxim =(select ifnull(max(idRelUsuarios),0)+1 from relusuarios);
insert into heatmap.relusuarios (idRelusuarios, idUsuario1, idUsuario2) values (maxim, idUsu, idfriend);
/* La siguiente linea es temporal ya que es la confirmacion del otro usuario(amigo) */
insert into heatmap.relusuarios (idRelusuarios, idUsuario1, idUsuario2) values (maxim+1, idfriend, idUsu);
select 'Se ha añadido un nuevo contacto',tipofinal,tiporel1,tiporel2,cuenta,cuenta2;
end case;
end
// delimiter ;


drop procedure if exists sp_Conversacion;

delimiter //
create procedure sp_Conversacion(in id1 int ,in id2  int)
begin
declare usu1 nvarchar(90);
declare usu2 nvarchar (90);
declare conbi1 nvarchar (180);
declare conbi2 nvarchar (180);
declare existe int;
set usu1 = (select nombre from usuario where idUsuario=id1);
set usu2 = (select nombre from usuario where idUsuario=id2);
set conbi1 = (concat(usu1,'-',usu2));
set conbi2 = (concat(usu2,'-',usu1));
set existe=(select count(*)  from Conversacion where título=conbi1 or título=conbi2);
if existe=0 then
select conbi1 as 'idConversacion';
else
select idConversacion from Conversacion where título=conbi1 or título=conbi2;
end if;
end
// delimiter ;


/*--------------------------VISTAS-------------------------*/

drop view if exists vw_MensajesConversacion;
create view vw_MensajesConversacion as select M.mensaje  , M.receptor  ,
M.remitente ,M.fecha_hora , C.idConversacion, C.título from Mensajes 
as M inner join Conversacion as C where M.idConversacion = C.idConversacion;

drop view if exists vw_Usuarios;
create view vw_Usuarios as select u.nombre,
u.correo , u.apellido ,  u.sexo , u.imagen
from usuario as u;

drop view if exists vw_ReportesDelictivos;
create view vw_ReportesDelictivos as select rep.titulo,rep.fecha,
day(rep.fecha) as 'dia', ub.coordenadax ,
ub.coordenaday from reportedelictivo as rep
inner join ubicacion as ub on ub.idubicacion=rep.idubicacion
order by TIMESTAMPDIFF(day,rep.fecha,curdate());

/*----------DATOS INSERTADOS----------*/

/*------------lenguaje---------------------*/


insert into lenguaje values(1,'es','Español','Lenguaje',
'Bienvenido a Heatmap',
'Heatmap es la herramienta que te permitirá iteractuar en tu ambiente de forma rápida y eficaz',
'Iniciar Sesión',
'Cerrar Sesión',
'Usuario',
'Teléfono, Correo o Usuario',
'Contraseña',
'Entrar',
'¿Olvidaste tu contraseña?',
'&iquest;Olvidaste tu contraseña&#63;',
'Busca tu correo electrónico con el que te registaste',
'Ingresa tu nueva contraseña',
'Confirma tu nueva contraseña',
'&iquest;Aún no tienes una cuenta en HeatMap&#63;',
'Registrate aquí',
'Registrate',
'Nombre',
'Apellido',
'Correo Electrónico',
'Sexo',
'Masculino',
'Femenino',
'Teléfono',
'Imagen',
'Fecha de Nacimiento',
'Usuario o contraseña incorrectos',
'Usuario agregado satisfactoriamente',
'Usuario modificado satisfactoriamente',
'Se ha cerrado la cuenta',
'&iquest;Estas seguro de que deseas cerrar tu cuenta;a&#63;',
'Usuario no borrado',
'Usuario encontrado',
'El usuario ya existe',
'El usuario no existe',
'Usuario no válido',
'Usuario confirmado exitosamente',
'Usuario no confirmado',
'Contraseña cambiada exitosamente',
'Contraseña no cambiada',
'Lugar agregado exitosamente',
'Lugar modificado satisfactoriamente',
'Lugar borrado',
'El lugar ya existe',
'El lugar no existe',
'Reporte agregado exitosamente',
'Reporte modificado',
'Reporte borrado',
'El reporte ya existe',
'El reporte no existe',
'Anuncio agregado satisfactoriamente',
'Anuncio modificado',
'Anuncio borrado',
'El anuncio ya existe',
'El anuncio no existe',
'Anuncio dado de alta en categoría exitosamente',
'El teléfono ya existe',
'Teléfono no válido',
'El correo electrónico ya esta en uso',
'El correo electrónico no es válido',
'Coordenadas no válidas',
'Fecha no válida',
'Horario no válido',
'Opcivón de sexo no válida',
'No dejes campos vacíos',
'Error',
'Mapa',
'Mapa General',
'Mapa Delictivo',
'Mapa Lugares Favoritos',
'Mapa Anuncios',
'Aun no tienes una conversación',
'No hay resultados con la búsqueda',
'Agrega nuevo contacto',
'Agregar',
'Nuevo reporte delictivo',
'Agregar aquí un reporte delictivo',
'Nuevo lugar favorito',
'Agregar aquí un lugar favorito',
'Nuevo anuncio',
'Agregar aquí un anuncio',
'Título',
'Lugar',
'Tipo de acto criminal',
'Fecha del acontecimiento',
'Hora del acontecimiento',
'Horario de abertura',
'Horario de cierre',
'Descripción',
'Estadísticas',
'Amigos',
'Amigo',
'Ubicación',
'Conversación',
'Mensajes',
'Enviar',
'Compartir lugar',
'Calificar lugar',
'Calificación',
'Categorías',
'La categoría no existe',
'Comida',
'Entretenimiento',
'Servicios',
'Lunes',
'Martes',
'Miercoles',
'Jueves',
'Viernes',
'Sábado',
'Domingo',
'Configuración',
'Mi Perfil',
'Membresia',
'Administrar Cuenta',
'Error, página no encontrada :(',
'Se ha enviado un correo a tu email para que confirmes tu cuenta',
'Confirmacion de cuenta',
'Has concluido exitosamente tu registro en HeatMap, para continuar con el proceso de registro da click en el siguiente enlace:',
'Se ha enviado un correo a tu email para que cambies tu contraseña',
'Cambio de contraseña en HeatMap',
'Has solicitado un cambio de contraseña en HeatMap, para proceder con el cambio de contraseña da click en el siguiente link:',
'Contraseña no Valida',
'La longitud del nombre debe ser menor a 16 caracteres',
'La contraseña debe tener las siguientes características: \\n-Mínimo 8 caracteres \\n-Al menos una minúscula \\n-Al menos una mayúscula \\n-Al menos un número',
'Los campos marcados son obligatorios',
'Las contraseñas no coinciden',
'Actos criminales en los pasados diez dias',
'Repórtalo al MP Virtual',
'Tipo de Siniestro',
'Seleccionar...',
'Bolseo',
'Asalto a mano armada',
'Asalto en vehiculo',
'Asesinato',
'Fecha del acontecimiento',
'Opción de tipo de siniestro no válida',
'Mostrar contrase&ntilde;a',
'Se ha enviado un correo a tu cuenta email para que cambies tu contraseña',
'Para cambiar tu contraseña da click en el siguiente enlace:',
'Cambio de Contraseña en HeatMap',
'Buscar',
'Se ha solicitado un cambio de tu contraseña para tu cuenta en HeatMap, si este no has sido tú, arreglaremos el problema',
'Se te ha enviado un correo para que cambies tu contraseña',
'Contraseña Cambiada',
'Rutas',
'Rutas en Heatmap',
'Punto de inicio',
'Punto a ir',
'Tipo de ruta',
'Mapa',
'En carro',
'Caminando',
'Obtener Ruta',
'Punto Inexacto',
'Cualquier Punto',
'Buscar',
'Foro de chat',
'Tu conversación con ',
'No se han encontrado resultados',
'Ruta guardada',
'La ruta ya existe',
'La ruta no existe'
);

insert into lenguaje values(2,'en','English','Language',
'Welcome to Heatmap',
'Heatmap is the tool that help you to interact on you enviroment quickly and easily',
'Log In',
'Log Out',
'User',
'Phone Number, Email or User',
'Password',
'Submit',
'You forgot your password?',
'You forgot your password?',
'Search the email you used to sign up',
'Type your new password',
'Confirm your new password',
'You don&#39;t have an account on Heatmap yet?',
'Sign Up',
'Sign Up',
'Name',
'Last Name',
'Email',
'Sex',
'Male',
'Female',
'Phone Number',
'Image',
'Birthday',
'User or password incorrectly',
'User created succesfully',
'Changes saved',
'You unabled your account',
'Are you sure you want to unable your account?',
'User not unabled',
'User found',
'This user already exists',
'This user don&#39;t exists',
'User not valid',
'User confirmed succesfully',
'User not confirmed',
'Password uptaded succesfully',
'Password couldn&#39;t changed',
'Point saved succesfully',
'Point updated',
'Point deleted',
'The point already exists',
'The point doesn&#39;t exists',
'Crime reported',
'Crime report changed',
'Crime report deleted',
'The crime report already exists',
'The crime report doesn&#39;t exists',
'Commercial created',
'Commercial changed',
'Commercial delted',
'The commercial already exists',
'The commercial doesn&#39;t exists',
'The commercial has been added now to the category',
'The phone number already exists',
'The phone nomber is not valid',
'The email is used by another account',
'The email is not valid',
'The coordinates are not valid',
'The date is not valid',
'The time is not valid',
'The sex option is not valid',
'Don´t let blank fields',
'Error',
'Map',
'General Map',
'Criminal Map',
'Your Points Map',
'Commercial Map',
'You don&#39;t have a conversation with this friend',
'No matches were found',
'Add a new friend',
'Create',
'New crime report',
'Create here a new crime report',
'New point',
'Create here a new point',
'New commercial',
'Create here a commercial',
'Title',
'Place',
'Type of crime',
'Date when happened',
'Time when happened',
'Time when opening',
'Time when closing',
'Description',
'Statistics',
'Friends',
'Friend',
'Location',
'Conversation',
'Messages',
'Send',
'Share place',
'Rate place',
'Rating',
'Categories',
'The category doesn&#39;t exists',
'Food',
'Entertainment',
'Services',
'Monday',
'Tuesday',
'Wednesday',
'Thursday',
'Friday',
'Saturday',
'Sunday',
'Settings',
'Profile',
'Suscription',
'Manage Account',
'Error, page not found :(',
'We have just sent to you an email to confirm your account',
'Account Confirmation',
'You have succesfully created your account on HeatMap, click on the follow link to confirm your account:',
'Check your inbox email to change your password',
'Change Password on HeatMap',
'To complete the password change follow the next link:',
'Invalid Format of Password',
'The name format is too long',
'The password must have the following format: \\n-8 Characters at least \\n-One character on lower case \\n-One character on upper case \\n-At least one number',
'The marked fields must be completed',
'The passwords are not the same',
'Crimes during the last ten days',
'Report on the Virtual MP',
'Kind of Crime',
'Select...',
'Pick-pocket',
'Burglarly',
'Burglarly by vehicle',
'Murdered',
'Date when happened',
'Incorrect option of kind of crime',
'Show password',
'We´ve just sent you an email to change your password',
'To change your password click on the following link:',
'Change your Password on HeatMap',
'Search',
'You forgot your password? Follow the link below to change your password',
'Check your email inbox to continue the steps to change your password',
'Password changed',
'Routes',
'Routes on Heatmap',
'Starting spot',
'Spot to go',
'Type of route',
'Map',
'By Car',
'Walking',
'Get Route',
'Uncertainly spot',
'Any spot',
'Search',
'Chat Forum',
'Your conversation with ',
'We couldnt found anyone with your query',
'Route created succesfully',
'The route already exists',
'The route doesn&#39;t exists'
);

/*---menu---*/



call sp_AltaMenu('nvar',1.0,'E','','sMapa','');
call sp_AltaMenu('nvar',1.1,'E','','sMapaGeneral','/heatmap/MapaGeneral');
call sp_AltaMenu('nvar',1.2,'E','','sMapaDelictivo','/heatmap/MapaDelictivo');
call sp_AltaMenu('nvar',1.3,'E','','sRutas','/heatmap/Rutas');
call sp_AltaMenu('nvar',2,'E','','sAlta','');
call sp_AltaMenu('nvar',2.1,'E','','sAltaLugar','/heatmap/AltaLugar1');
call sp_AltaMenu('nvar',2.2,'E','','sAltaReporteDelictivo','/heatmap/AltaReporteDelictivo1');
call sp_AltaMenu('nvar',7,'E','','sBuscador','/heatmap/Buscador');
call sp_AltaMenu('nvar',8,'E','','sAmigos','/heatmap/Amigos');
call sp_AltaMenu('nvar',9,'E','','sForo','/heatmap/Foro');


call sp_AltaMenu('mapa',1,'E','','sAltaLugarAqui','/heatmap/AltaLugar1');
call sp_AltaMenu('mapa',2,'E','','sAltaReporteDelictivoAqui','/heatmap/AltaReporteDelictivo1');

call sp_BajaMenu('');

/* usuarios */
call sp_AltaUsuario('emetion1',
'45690b6e656da45099fa44aafe6af6c6c7085888','emetion1@gmail.com' ,'Emanuel', 
'Barrera','1998-09-18', 'M','','5540891179',null,null,'codigo1');
call sp_AltaUsuario('shavo1',
'45690b6e656da45099fa44aafe6af6c6c7085888', 'shavo1@gmail.com','Ulises', 
'Perez','1998-09-18', 'M','','5564842359','19.414615', '-99.072393','codigo2');
call sp_AltaUsuario('shavo2',
'45690b6e656da45099fa44aafe6af6c6c7085888', 'shavo2@gmail.com','Ulises', 
'Perez','1998-09-18', 'M','','5525089914','19.4056037','-99.0829458','codigo3');
call sp_AltaUsuario('morguis',
'45690b6e656da45099fa44aafe6af6c6c7085888','morguis@hotmail.com','Jose',
'Morgado','1998-09-18', 'M','',  '5544520458','19.472394', '-99.157497','codigo4');
call sp_AltaUsuario('emetion4',
'45690b6e656da45099fa44aafe6af6c6c7085888','emetion4@gmail.com','Emanuel', 
'Barrera','1998-09-18', 'M','','5512258490','19.458305', '-99.121528','codigo5');
call sp_AltaUsuario('moythou',
'e3c5cfe11e78747de9b1751d8d5f98a6f698227e','moivp.11@hotmail.com','Moy', 
'Thou','1998-09-18', 'M','','5540891178',null,null,'codigo6');
call sp_AltaUsuario('olamundo',
'e3c5cfe11e78747de9b1751d8d5f98a6f698227e','olamundo@gmail.com','Moy', 
'Thou','1998-09-18', 'M','','5513047952','19.444199', '-99.116565','codigo7');
call sp_ModificaUsuario('emetion5', 'emetion5', 'Benito',
 'Camelo', '1998-06-12', 'F', '', '5512258390', null, null);
call sp_ConfirmacionUsuario('codigo1');
call sp_ConfirmacionUsuario('codigo2');
call sp_ConfirmacionUsuario('codigo3');
call sp_ConfirmacionUsuario('codigo4');
call sp_ConfirmacionUsuario('codigo5');
call sp_ConfirmacionUsuario('codigo6');


/* reportes */
call sp_AltaReporteDelictivo('emetion4',
'reporte','descripcion','19.405925', '-99.117149','tipo');
call sp_AltaLugar('emetion4',
'19.405925', '-99.117149','lugar','imagen');
call sp_ModificaLugar('emetion4',
'lugar','19.4059285', '-99.838','nuevo_lugar','');
call sp_AltaAnuncio('emetion1',
'19.405925', '-99.117149','anuncio','descripcion','imagen','8:00','15:00');
call sp_ModificaAnuncio('emetion1',
'19.405925', '-99.117149','anuncio','anuncio',
'descripcion','','8:00','15:30');
call sp_AltaRuta('emetion34','a mi house','Metro Popotla','Metro la Raza');
call sp_ConsultaRutas('emetion34');
call sp_AltaCategoria('sComida','http://i.imgur.com/JLjaPQl.png','#EB1349',1);
call sp_AltaCategoria('sEntretenimiento','http://i.imgur.com/J9ZRuAH.png','#43A131',2);
call sp_AltaCategoria('sServicios','http://i.imgur.com/VkXgWtZ.png','#7082C9',2);
/* red social */
call sp_buscador('emanuel                                              Barrera');
call sp_NuevoMensaje( 'morguis',
 'emetion4', 'hola');
call sp_NuevoMensaje('emetion1',
'shavo1' , 'Hola shavo');
call sp_ConsultaMensajes('morguis',
'shavo1');
call sp_Amigos(0,1,0);
select * from relUsuarios;
call sp_ConsultaMensajes('morguis',
 'emetion4');
call sp_Conversacion(1,4);

/*otros metodos*/
call sp_login('5540891179','45690b6e656da45099fa44aafe6af6c6c7085888');
select * from usuario;
select * from lenguaje;
select * from rutas;
select * from anuncio;
select * from categoria;
select * from reportedelictivo;
select * from vw_Usuarios;
call sp_ConsultaReportesPorTipo('');
call sp_LugaresUsuario('emetion1');
call sp_ConsultaAnuncios('sEntretenimiento');
select * from lugar;
call sp_menu('morguis','nvar');
call sp_ConsultaMensajes('emetion1', 'morguis');
select * from mensajes;
call sp_Amigos(0,2,0);
use heatmap;
