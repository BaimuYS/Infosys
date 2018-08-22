DROP TABLE IF EXISTS TB_DEPARTMENT;

DROP TABLE IF EXISTS TB_EMP_POSITION;

DROP TABLE IF EXISTS TB_EMPLOYEE;

DROP TABLE IF EXISTS TB_MENU;

DROP TABLE IF EXISTS TB_POSITION;

DROP TABLE IF EXISTS TB_ROLE;

DROP TABLE IF EXISTS TB_ROLE_MENU;

DROP TABLE IF EXISTS TB_USER;

DROP TABLE IF EXISTS TB_USER_ROLE;

/*==============================================================*/
/* Table: TB_DEPARTMENT                                         */
/*==============================================================*/
CREATE TABLE TB_DEPARTMENT
(
   DEPT_ID              VARCHAR(10) NOT NULL COMMENT '部门ID',
   DEPT_NAME            VARCHAR(100) NOT NULL COMMENT '部门名称',
   DEPT_CODE            VARCHAR(20) NOT NULL COMMENT '部门编码',
   PAR_DEPT_CODE        VARCHAR(20) COMMENT '上级部门编码',
   PRIMARY KEY (DEPT_ID)
);

ALTER TABLE TB_DEPARTMENT COMMENT '部门信息表';

/*==============================================================*/
/* Table: TB_EMP_POSITION                                       */
/*==============================================================*/
CREATE TABLE TB_EMP_POSITION
(
   EMP_ID               VARCHAR(12) NOT NULL COMMENT '员工号',
   POSITION_ID          VARCHAR(10) NOT NULL COMMENT '岗位ID',
   PRIMARY KEY (EMP_ID, POSITION_ID)
);

ALTER TABLE TB_EMP_POSITION COMMENT '员工-岗位';

/*==============================================================*/
/* Table: TB_EMPLOYEE                                           */
/*==============================================================*/
CREATE TABLE TB_EMPLOYEE
(
   EMP_ID               VARCHAR(12) NOT NULL COMMENT '员工号',
   EMP_NAME             VARCHAR(50) COMMENT '姓名',
   CUST_ID              VARCHAR(20) COMMENT '客户号',
   BRANCH_ID            VARCHAR(10) COMMENT '分行编号',
   EMAIL                VARCHAR(50) COMMENT '邮件地址',
   MOBILE               VARCHAR(15) COMMENT '手机号码',
   TEL                  VARCHAR(15) COMMENT '座机',
   ADDRESS              VARCHAR(200) COMMENT '住址',
   ID_CARD              VARCHAR(20) COMMENT '身份证',
   SEX                  VARCHAR(6) COMMENT '性别',
   IN_DT                DATE COMMENT '入职日期',
   OUT_DT               DATE COMMENT '离职日期',
   PRIMARY KEY (EMP_ID)
);

ALTER TABLE TB_EMPLOYEE COMMENT '员工信息';

/*==============================================================*/
/* Table: TB_MENU                                               */
/*==============================================================*/
CREATE TABLE TB_MENU
(
   MENU_ID              VARCHAR(50) NOT NULL COMMENT '菜单ID',
   NAME                 VARCHAR(100) NOT NULL COMMENT '菜单名称',
   URL                  VARCHAR(200) NOT NULL COMMENT '菜单URL',
   PAR_MENU_ID          VARCHAR(50) COMMENT '上级菜单ID',
   MENU_ORDER           INT COMMENT '菜单顺序',
   ICON_CLASS           VARCHAR(100) COMMENT '图标类',
   PRIMARY KEY (MENU_ID)
);

ALTER TABLE TB_MENU COMMENT '菜单列表';

/*==============================================================*/
/* Table: TB_POSITION                                           */
/*==============================================================*/
CREATE TABLE TB_POSITION
(
   POSITION_ID          VARCHAR(10) NOT NULL COMMENT '岗位ID',
   DEPT_ID              VARCHAR(10) NOT NULL,
   POSITION_CODE        VARCHAR(20) NOT NULL COMMENT '岗位编码',
   POSITION_NAME        VARCHAR(100) NOT NULL COMMENT '岗位名称',
   PAR_POS_CODE         VARCHAR(20) COMMENT '上级岗位编码',
   PRIMARY KEY (POSITION_ID)
);

ALTER TABLE TB_POSITION COMMENT '岗位信息表';

/*==============================================================*/
/* Table: TB_ROLE                                               */
/*==============================================================*/
CREATE TABLE TB_ROLE
(
   ROLE_ID              VARCHAR(50) NOT NULL,
   ROLE_NAME            VARCHAR(100),
   ROLE_DESC            VARCHAR(500),
   PRIMARY KEY (ROLE_ID)
);

ALTER TABLE TB_ROLE COMMENT '角色列表';

/*==============================================================*/
/* Table: TB_ROLE_MENU                                          */
/*==============================================================*/
CREATE TABLE TB_ROLE_MENU
(
   ROLE_ID              VARCHAR(50) NOT NULL COMMENT '角色ID',
   MENU_ID              VARCHAR(50) NOT NULL COMMENT '菜单ID',
   PRIMARY KEY (ROLE_ID, MENU_ID)
);

ALTER TABLE TB_ROLE_MENU COMMENT '角色-菜单';

/*==============================================================*/
/* Table: TB_USER                                               */
/*==============================================================*/
CREATE TABLE TB_USER
(
   USERNAME             VARCHAR(12) NOT NULL COMMENT '登录账号',
   EMP_ID               VARCHAR(12) NOT NULL COMMENT '员工号',
   PASSWORD             VARCHAR(100) COMMENT '登录密码',
   LOGIN_COUNT          INT COMMENT '登录次数',
   LAST_TIME            TIMESTAMP COMMENT '最后登录时间',
   PRIMARY KEY (USERNAME)
);

ALTER TABLE TB_USER COMMENT '用户信息表';

/*==============================================================*/
/* Table: TB_USER_ROLE                                          */
/*==============================================================*/
CREATE TABLE TB_USER_ROLE
(
   USERNAME             VARCHAR(12) NOT NULL COMMENT '登录账号',
   ROLE_ID              VARCHAR(50) NOT NULL COMMENT '角色ID',
   PRIMARY KEY (USERNAME, ROLE_ID)
);

ALTER TABLE TB_USER_ROLE COMMENT '用户信息-角色';

ALTER TABLE TB_EMP_POSITION ADD CONSTRAINT FK_TB_EMP_POSITION FOREIGN KEY (EMP_ID)
      REFERENCES TB_EMPLOYEE (EMP_ID) ON DELETE RESTRICT ON UPDATE RESTRICT;

ALTER TABLE TB_EMP_POSITION ADD CONSTRAINT FK_TB_EMP_POSITION2 FOREIGN KEY (POSITION_ID)
      REFERENCES TB_POSITION (POSITION_ID) ON DELETE RESTRICT ON UPDATE RESTRICT;

ALTER TABLE TB_POSITION ADD CONSTRAINT FK_TB_DEPT_POS FOREIGN KEY (DEPT_ID)
      REFERENCES TB_DEPARTMENT (DEPT_ID) ON DELETE RESTRICT ON UPDATE RESTRICT;

ALTER TABLE TB_ROLE_MENU ADD CONSTRAINT FK_TB_ROLE_MENU FOREIGN KEY (ROLE_ID)
      REFERENCES TB_ROLE (ROLE_ID) ON DELETE RESTRICT ON UPDATE RESTRICT;

ALTER TABLE TB_ROLE_MENU ADD CONSTRAINT FK_TB_ROLE_MENU2 FOREIGN KEY (MENU_ID)
      REFERENCES TB_MENU (MENU_ID) ON DELETE RESTRICT ON UPDATE RESTRICT;

ALTER TABLE TB_USER ADD CONSTRAINT FK_EMP_LOGIN FOREIGN KEY (EMP_ID)
      REFERENCES TB_EMPLOYEE (EMP_ID) ON DELETE RESTRICT ON UPDATE RESTRICT;

ALTER TABLE TB_USER_ROLE ADD CONSTRAINT FK_TB_USER_ROLE FOREIGN KEY (USERNAME)
      REFERENCES TB_USER (USERNAME) ON DELETE RESTRICT ON UPDATE RESTRICT;

ALTER TABLE TB_USER_ROLE ADD CONSTRAINT FK_TB_USER_ROLE2 FOREIGN KEY (ROLE_ID)
      REFERENCES TB_ROLE (ROLE_ID) ON DELETE RESTRICT ON UPDATE RESTRICT;
