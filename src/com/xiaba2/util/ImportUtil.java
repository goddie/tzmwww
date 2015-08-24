package com.xiaba2.util;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.Column;
import javax.sql.DataSource;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.xiaba2.invest.domain.Product;

public class ImportUtil {

	public static List<Product> loadProduct() {

		List<Product> list = new ArrayList<Product>();
		
		BasicDataSource ds = new BasicDataSource();
		int result = 0;
		try {

			ds.setDriverClassName("com.mysql.jdbc.Driver");
			ds.setUrl("jdbc:mysql://222.73.249.70:3306/db_touzimao");
			ds.setUsername("root");
			ds.setPassword("goddie");
			ds.setInitialSize(50);
			ds.setMaxIdle(30);
			ds.setMaxWaitMillis(10000);

			String sql = "select * from t_product";

			QueryRunner qr = new QueryRunner(ds);
			@SuppressWarnings("unchecked")

			List qlist = (List) qr.query(sql, new BeanListHandler(ProductData.class));
			
			
			for (int i = 0; i < qlist.size(); i++) {
				
				ProductData productData = (ProductData) qlist.get(i);

 
				Product product = new Product();
				
				// 赋值
				BeanUtils.copyProperties(product, productData);
				
				list.add(product);
				
			}
			
			
			
			
			
			

		} catch (Exception e) {
			e.printStackTrace();
		} finally {

			try {
				ds.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}

		return list;
	}

	public static class ProductData {
		/**
		 * 产品编号 BIGINT
		 */

		private int CPBH;

		/**
		 * 产品简称 VARCHAR(50)
		 */

		private String CPJC;

		/**
		 * 产品名称 VARCHAR(200)
		 */

		private String CPMC;

		/**
		 * 产品期限 INT
		 */

		private int CPQX;

		/**
		 * 产品系列 TINYINT
		 */

		private int CPXL;

		/**
		 * 发行费用最低 INT
		 */

		private int FXFYZD;

		/**
		 * 发行费用最高 INT
		 */

		private int FXFYZG;

		/**
		 * 发行费用说明 VARCHAR(500)
		 */

		private String FXFYSM;

		/**
		 * 收益率最低 INT
		 */

		private int SYLZD;

		/**
		 * 收益率最高 INT
		 */

		private int SYLZG;

		/**
		 * 收益率说明 VARCHAR(500)
		 */

		private String SYLSM;

		/**
		 * 收益类型 TINYINT
		 */

		private int SYLX;

		/**
		 * 收益类型说明 VARCHAR(100)
		 */

		private String SYLXSM;

		/**
		 * 付息方式 TINYINT
		 */

		private int FXFS;

		/**
		 * 投资领域 TINYINT
		 */

		private int TZLY;

		/**
		 * 所在区域 TINYINT
		 */

		private int SZQY;

		/**
		 * 大小配比 TINYINT
		 */

		private int DXPB;

		/**
		 * 大小配比说明 VARCHAR(100)
		 */

		private String DXPBSM;

		/**
		 * 质押物 VARCHAR(20)
		 */

		private String ZYW;

		/**
		 * 风险等级 TINYINT
		 */

		private int FXDJ;

		/**
		 * 发行机构 BIGINT
		 */

		private int FXJG;

		/**
		 * 规模 BIGINT
		 */

		private int GUIMO;

		/**
		 * 已进款金额 BIGINT
		 */

		private int YJKJE;

		/**
		 * 投资门槛额度 BIGINT
		 */

		private int TZMKED;

		/**
		 * 募集账号 VARCHAR(300)
		 */

		private String MJZH;

		/**
		 * 是否推荐 TINYINT
		 */

		private int SFTJ;

		/**
		 * 是否包销 TINYINT
		 */

		private int SFBX;

		/**
		 * 推荐星级 INT
		 */

		private int TJXJ;

		/**
		 * 结款时点 TINYINT
		 */

		private int JKSD;

		/**
		 * 结款时点说明 VARCHAR(100)
		 */

		private String JKSDSM;

		/**
		 * 起息日 CHAR(10)
		 */

		private String QXRQ;

		/**
		 * 到期日 CHAR(10)
		 */

		private String DQRQ;

		/**
		 * 进度说明 TEXT
		 */

		private String JDSM;

		/**
		 * 融资方介绍 TEXT
		 */

		private String RZFJS;

		/**
		 * 短信模板 TEXT
		 */

		private String DXMB;

		/**
		 * 资金用途 TEXT
		 */

		private String ZJYT;

		/**
		 * 风控措施 TEXT
		 */

		private String FKCS;

		/**
		 * 还款来源 TEXT
		 */

		private String HKLY;

		/**
		 * 项目亮点 TEXT
		 */

		private String XMLD;

		/**
		 * 产品说明 TEXT
		 */

		private String CPSM;

		/**
		 * 产品文档url VARCHAR(500)
		 */

		private String CPWDURL;

		/**
		 * 产品状态 TINYINT
		 */

		private int CPZT;

		/**
		 * 是否有效 TINYINT
		 */

		private int SFYX;

		/**
		 * 产品经理 BIGINT
		 */

		private int CPJL;

		/**
		 * 产品经理姓名 VARCHAR(50)
		 */

		private String CPJLXM;

		public int getCPBH() {
			return CPBH;
		}

		public void setCPBH(int cPBH) {
			CPBH = cPBH;
		}

		public String getCPJC() {
			return CPJC;
		}

		public void setCPJC(String cPJC) {
			CPJC = cPJC;
		}

		public String getCPMC() {
			return CPMC;
		}

		public void setCPMC(String cPMC) {
			CPMC = cPMC;
		}

		public int getCPQX() {
			return CPQX;
		}

		public void setCPQX(int cPQX) {
			CPQX = cPQX;
		}

		public int getCPXL() {
			return CPXL;
		}

		public void setCPXL(int cPXL) {
			CPXL = cPXL;
		}

		public int getFXFYZD() {
			return FXFYZD;
		}

		public void setFXFYZD(int fXFYZD) {
			FXFYZD = fXFYZD;
		}

		public int getFXFYZG() {
			return FXFYZG;
		}

		public void setFXFYZG(int fXFYZG) {
			FXFYZG = fXFYZG;
		}

		public String getFXFYSM() {
			return FXFYSM;
		}

		public void setFXFYSM(String fXFYSM) {
			FXFYSM = fXFYSM;
		}

		public int getSYLZD() {
			return SYLZD;
		}

		public void setSYLZD(int sYLZD) {
			SYLZD = sYLZD;
		}

		public int getSYLZG() {
			return SYLZG;
		}

		public void setSYLZG(int sYLZG) {
			SYLZG = sYLZG;
		}

		public String getSYLSM() {
			return SYLSM;
		}

		public void setSYLSM(String sYLSM) {
			SYLSM = sYLSM;
		}

		public int getSYLX() {
			return SYLX;
		}

		public void setSYLX(int sYLX) {
			SYLX = sYLX;
		}

		public String getSYLXSM() {
			return SYLXSM;
		}

		public void setSYLXSM(String sYLXSM) {
			SYLXSM = sYLXSM;
		}

		public int getFXFS() {
			return FXFS;
		}

		public void setFXFS(int fXFS) {
			FXFS = fXFS;
		}

		public int getTZLY() {
			return TZLY;
		}

		public void setTZLY(int tZLY) {
			TZLY = tZLY;
		}

		public int getSZQY() {
			return SZQY;
		}

		public void setSZQY(int sZQY) {
			SZQY = sZQY;
		}

		public int getDXPB() {
			return DXPB;
		}

		public void setDXPB(int dXPB) {
			DXPB = dXPB;
		}

		public String getDXPBSM() {
			return DXPBSM;
		}

		public void setDXPBSM(String dXPBSM) {
			DXPBSM = dXPBSM;
		}

		public String getZYW() {
			return ZYW;
		}

		public void setZYW(String zYW) {
			ZYW = zYW;
		}

		public int getFXDJ() {
			return FXDJ;
		}

		public void setFXDJ(int fXDJ) {
			FXDJ = fXDJ;
		}

		public int getFXJG() {
			return FXJG;
		}

		public void setFXJG(int fXJG) {
			FXJG = fXJG;
		}

		public int getGUIMO() {
			return GUIMO;
		}

		public void setGUIMO(int gUIMO) {
			GUIMO = gUIMO;
		}

		public int getYJKJE() {
			return YJKJE;
		}

		public void setYJKJE(int yJKJE) {
			YJKJE = yJKJE;
		}

		public int getTZMKED() {
			return TZMKED;
		}

		public void setTZMKED(int tZMKED) {
			TZMKED = tZMKED;
		}

		public String getMJZH() {
			return MJZH;
		}

		public void setMJZH(String mJZH) {
			MJZH = mJZH;
		}

		public int getSFTJ() {
			return SFTJ;
		}

		public void setSFTJ(int sFTJ) {
			SFTJ = sFTJ;
		}

		public int getSFBX() {
			return SFBX;
		}

		public void setSFBX(int sFBX) {
			SFBX = sFBX;
		}

		public int getTJXJ() {
			return TJXJ;
		}

		public void setTJXJ(int tJXJ) {
			TJXJ = tJXJ;
		}

		public int getJKSD() {
			return JKSD;
		}

		public void setJKSD(int jKSD) {
			JKSD = jKSD;
		}

		public String getJKSDSM() {
			return JKSDSM;
		}

		public void setJKSDSM(String jKSDSM) {
			JKSDSM = jKSDSM;
		}

		public String getQXRQ() {
			return QXRQ;
		}

		public void setQXRQ(String qXRQ) {
			QXRQ = qXRQ;
		}

		public String getDQRQ() {
			return DQRQ;
		}

		public void setDQRQ(String dQRQ) {
			DQRQ = dQRQ;
		}

		public String getJDSM() {
			return JDSM;
		}

		public void setJDSM(String jDSM) {
			JDSM = jDSM;
		}

		public String getRZFJS() {
			return RZFJS;
		}

		public void setRZFJS(String rZFJS) {
			RZFJS = rZFJS;
		}

		public String getDXMB() {
			return DXMB;
		}

		public void setDXMB(String dXMB) {
			DXMB = dXMB;
		}

		public String getZJYT() {
			return ZJYT;
		}

		public void setZJYT(String zJYT) {
			ZJYT = zJYT;
		}

		public String getFKCS() {
			return FKCS;
		}

		public void setFKCS(String fKCS) {
			FKCS = fKCS;
		}

		public String getHKLY() {
			return HKLY;
		}

		public void setHKLY(String hKLY) {
			HKLY = hKLY;
		}

		public String getXMLD() {
			return XMLD;
		}

		public void setXMLD(String xMLD) {
			XMLD = xMLD;
		}

		public String getCPSM() {
			return CPSM;
		}

		public void setCPSM(String cPSM) {
			CPSM = cPSM;
		}

		public String getCPWDURL() {
			return CPWDURL;
		}

		public void setCPWDURL(String cPWDURL) {
			CPWDURL = cPWDURL;
		}

		public int getCPZT() {
			return CPZT;
		}

		public void setCPZT(int cPZT) {
			CPZT = cPZT;
		}

		public int getSFYX() {
			return SFYX;
		}

		public void setSFYX(int sFYX) {
			SFYX = sFYX;
		}

		public int getCPJL() {
			return CPJL;
		}

		public void setCPJL(int cPJL) {
			CPJL = cPJL;
		}

		public String getCPJLXM() {
			return CPJLXM;
		}

		public void setCPJLXM(String cPJLXM) {
			CPJLXM = cPJLXM;
		}

	}

}
