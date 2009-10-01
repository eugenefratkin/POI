package org.apache.poi.xwpf.usermodel;

import java.math.BigInteger;

import org.apache.xmlbeans.impl.xb.xmlschema.SpaceAttribute.Space;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTDecimalNumber;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFonts;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTP;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPPr;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTR;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRPr;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSdtBlock;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSdtContentBlock;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSdtEndPr;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSdtPr;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTabStop;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTabs;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTText;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STFldCharType;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STOnOff;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STTabJc;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STTabTlc;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STTheme;

public class TOC {
	
	CTSdtBlock block;
	
	public TOC() {
		this(CTSdtBlock.Factory.newInstance());
	}
	
	public TOC(CTSdtBlock block) {
		this.block = block;
		CTSdtPr sdtPr = block.addNewSdtPr();
		CTDecimalNumber id = sdtPr.addNewId();
		id.setVal(new BigInteger("4844945"));
		sdtPr.addNewDocPartObj().addNewDocPartGallery().setVal("Table of contents");
		CTSdtEndPr sdtEndPr = block.addNewSdtEndPr();
		CTRPr rPr = sdtEndPr.addNewRPr();
		CTFonts fonts = rPr.addNewRFonts();
		fonts.setAsciiTheme(STTheme.MINOR_H_ANSI);
		fonts.setEastAsiaTheme(STTheme.MINOR_H_ANSI);
		fonts.setHAnsiTheme(STTheme.MINOR_H_ANSI);
		fonts.setCstheme(STTheme.MINOR_BIDI);
		rPr.addNewB().setVal(STOnOff.OFF);
		rPr.addNewBCs().setVal(STOnOff.OFF);
		rPr.addNewColor().setVal("auto");
		rPr.addNewSz().setVal(new BigInteger("24"));
		rPr.addNewSzCs().setVal(new BigInteger("24"));
		CTSdtContentBlock content = block.addNewSdtContent();
		CTP p = content.addNewP();
		p.setRsidR("00EF7E24".getBytes());
		p.setRsidRDefault("00EF7E24".getBytes());
		p.addNewPPr().addNewPStyle().setVal("TOCHeading");
		p.addNewR().addNewT().set("Table of Contents");
	}
	
	public CTSdtBlock getBlock() {
		return this.block;
	}
	
	public void addRow(int level, String title, int page, String bookmarkRef) {
		CTSdtContentBlock contentBlock = this.block.getSdtContent();
		CTP p = contentBlock.addNewP();
		p.setRsidR("00EF7E24".getBytes());
		p.setRsidRDefault("00EF7E24".getBytes());
		CTPPr pPr = p.addNewPPr();
		pPr.addNewPStyle().setVal("TOC" + level);
		CTTabs tabs = pPr.addNewTabs();
		CTTabStop tab = tabs.addNewTab();
		tab.setVal(STTabJc.RIGHT);
		tab.setLeader(STTabTlc.DOT);
		tab.setPos(new BigInteger("8290"));
		pPr.addNewRPr().addNewNoProof();
		CTR run = p.addNewR();
		run.addNewRPr().addNewNoProof();
		run.addNewT().set(title);
		run = p.addNewR();
		run.addNewRPr().addNewNoProof();
		run.addNewTab();
		run = p.addNewR();
		run.addNewRPr().addNewNoProof();
		run.addNewFldChar().setFldCharType(STFldCharType.BEGIN);
		// pageref run
		run = p.addNewR();
		run.addNewRPr().addNewNoProof();
		CTText text = run.addNewInstrText();
		text.setSpace(Space.PRESERVE);
		// bookmark reference
		text.set(" PAGEREF _Toc" + bookmarkRef + " \\h ");
		p.addNewR().addNewRPr().addNewNoProof();
		run = p.addNewR();
		run.addNewRPr().addNewNoProof();
		run.addNewFldChar().setFldCharType(STFldCharType.SEPARATE);
		// page number run
		run = p.addNewR();
		run.addNewRPr().addNewNoProof();
		run.addNewT().set(new Integer(page).toString());
		run = p.addNewR();
		run.addNewRPr().addNewNoProof();
		run.addNewFldChar().setFldCharType(STFldCharType.END);
		
	}

}
