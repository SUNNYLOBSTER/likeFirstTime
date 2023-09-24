package com.min.edu.model.service;

import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.text.StringEscapeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.min.edu.model.mapper.IMediChart_Dao;
import com.min.edu.vo.FileBoard_VO;
import com.min.edu.vo.MediChart_VO;
import com.min.edu.vo.MediCode_VO;
import com.min.edu.vo.PetsInfo_VO;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class MediChart_ServiceImpl implements IMediChart_Service {
	
	@Autowired
	private IMediChart_Dao dao;
	
	@Override
	public int countPet(String id) {
		log.info("&&&&& MediChart_ServiceImpl countPet 전달받은 파라미터값 : {} &&&&&", id);
		return dao.countPet(id);
	}

	@Override
	public List<PetsInfo_VO> searchPet(String id) {
		log.info("&&&&& MediChart_ServiceImpl searchPet 전달받은 파라미터값 : {} &&&&&", id);
		return dao.searchPet(id);
	}

	@Override
	public int insertNewPet(PetsInfo_VO pvo) {
		log.info("&&&&& MediChart_ServiceImpl insertNewPet 전달받은 파라미터값 : {} &&&&&", pvo);
		return dao.insertNewPet(pvo);
	}

	@Override
	public int deletePet(int pet_seq) {
		log.info("&&&&& MediChart_ServiceImpl deletePet 전달받은 파라미터값 : {} &&&&&", pet_seq);
		return dao.deletePet(pet_seq);
	}
	
//	@Transactional(readOnly = true)
	@Override
	public String insertNewChart(MediChart_VO mvo) {
		log.info("&&&&& MediChart_ServiceImpl insertNewChart &&&&&");
		log.info("&&&&& 전달받은 파라미터값 : {} &&&&&", mvo);
		int n = dao.insertNewChart(mvo);
//		fvo.setF_ref(mvo.getMedi_num());
//		int f = dao.fileUpload(fvo);
		String m = (n>0)?dao.getMaxSeq():"";
//		String m = (n>0 && f>=0)?dao.getMaxSeq():"";
		return m;
	}

	@Override
	public List<PetsInfo_VO> selectAllChart(String medi_id) {
		log.info("&&&&& MediChart_ServiceImpl selectAllChart &&&&&");
		log.info("&&&&& 전달받은 파라미터값 : {} &&&&&", medi_id);
		return dao.selectAllChart(medi_id);
	}

	@Override
	public List<PetsInfo_VO> selectPetChart(Map<String, Object> map) {
		log.info("&&&&& MediChart_ServiceImpl selectAllChart &&&&&");
		log.info("&&&&& 전달받은 파라미터값 : {} &&&&&", map);
		return dao.selectPetChart(map);
	}

	@Override
	public List<MediChart_VO> selectLChart(Map<String, Object> map) {
		log.info("&&&&& MediChart_ServiceImpl selectLChart &&&&&");
		log.info("&&&&& 전달받은 파라미터값 : {} &&&&&", map);
		return dao.selectLChart(map);
	}

	@Override
	public List<PetsInfo_VO> selectSChart(Map<String, Object> map) {
		log.info("&&&&& MediChart_ServiceImpl selectSChart &&&&&");
		log.info("&&&&& 전달받은 파라미터값 : {} &&&&&", map);
		return dao.selectSChart(map);
	}

	@Override
	public PetsInfo_VO selectOneChart(String medi_num) {
		log.info("&&&&& MediChart_ServiceImpl selectOneChart &&&&&");
		log.info("&&&&& 전달받은 파라미터값 : {} &&&&&", medi_num);
		return dao.selectOneChart(medi_num);
	}

	@Override
	public int modifyChart(Map<String, Object> map) {
		log.info("&&&&& MediChart_ServiceImpl modifyChart &&&&&");
		log.info("&&&&& 전달받은 파라미터값 : {} &&&&&", map);
		return dao.modifyChart(map);
	}

	@Override
	public int deleteChart(String medi_num) {
		log.info("&&&&& MediChart_ServiceImpl deleteChart &&&&&");
		log.info("&&&&& 전달받은 파라미터값 : {} &&&&&", medi_num);
		return dao.deleteChart(medi_num);
	}

	@Override
	public List<MediCode_VO> selectAllMediCode() {
		log.info("&&&&& MediChart_ServiceImpl selectAllMediCode &&&&&");
		return dao.selectAllMediCode();
	}

	@Override
	public MediCode_VO searchMediName(String medi_code) {
		log.info("&&&&& MediChart_ServiceImpl searchMediName &&&&&");
		return dao.searchMediName(medi_code);
	}

	@Override
	public String getDetail(String medi_num) {
		log.info("&&&&& MediChart_ServiceImpl getDetail &&&&&");
		return dao.getDetail(medi_num);
	}

	@Override
	public String createPdf(String medi_num) {
		log.info("&&&&& MediChart_ServiceImpl createPdf &&&&&");
		log.info("&&&&& 전달받은 파라미터값 : {} &&&&&", medi_num);
		
		String result = "";
		
		PetsInfo_VO pvo = dao.selectOneChart(medi_num);
		
		try {
        	
            Document document = new Document(); // pdf 문서를 처리하는 객체
            
            Date date = new Date(); 
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd-hh-mm-ss");
            String format_date = formatter.format(date);
 
            PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("C:/진료기록_"+format_date+".pdf"));
            
            document.open();
            
            BaseFont baseFont = BaseFont.createFont("C:/Windows/Fonts/H2GTRM.TTF", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
            
            Font font = new Font(baseFont, 12);
 
            PdfPTable table = new PdfPTable(5);
            Chunk chunk = new Chunk("진료기록", font);
            Paragraph ph = new Paragraph(chunk);
            ph.setAlignment(Element.ALIGN_CENTER);
            document.add(ph);
 
            document.add(Chunk.NEWLINE);
            document.add(Chunk.NEWLINE); 
            
            PdfPCell cell1 = new PdfPCell(new Phrase("반려동물", font));
            cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
 
            PdfPCell cell2 = new PdfPCell(new Phrase("진료일자", font));
            cell2.setHorizontalAlignment(Element.ALIGN_CENTER);
 
            PdfPCell cell3 = new PdfPCell(new Phrase("진료과목", font));
            cell3.setHorizontalAlignment(Element.ALIGN_CENTER);
            
            PdfPCell cell4 = new PdfPCell(new Phrase("세부과목",font));
            cell4.setHorizontalAlignment(Element.ALIGN_CENTER);
 
            PdfPCell cell5 = new PdfPCell(new Phrase("내용",font));
            cell5.setHorizontalAlignment(Element.ALIGN_CENTER);
            
            table.addCell(cell1); 
            table.addCell(cell2);
            table.addCell(cell3);
            table.addCell(cell4);
            table.addCell(cell5);
 
            String unescapedContent = StringEscapeUtils.unescapeHtml4(pvo.getMedichart_vo().get(0).getMedi_content());
            System.out.println(unescapedContent);
            
            String pattern1 = "<p>(.*?)</p>";
            Pattern p = Pattern.compile(pattern1);
            Matcher m = p.matcher(unescapedContent);
            
            String text = "";
            while(m.find()) {
            	for(int i=0; i<m.groupCount(); i++) {
            		text += m.group(i);
            	}
            }
            
            Matcher m2 = p.matcher(text);
            
            String final_text = "";
            while(m2.find()) {
            	String cuttedText = m2.group(1);
            	final_text += cuttedText;
            }
            
            PdfPCell pet_name = new PdfPCell(new Phrase(pvo.getPet_name(), font));
            PdfPCell medi_visit = new PdfPCell(new Phrase(pvo.getMedichart_vo().get(0).getMedi_visit(), font));
            PdfPCell medi_lname  = new PdfPCell(new Phrase(pvo.getMedichart_vo().get(0).getMedi_lname(), font));
            PdfPCell medi_sname = new PdfPCell(new Phrase(pvo.getMedichart_vo().get(0).getMedi_sname(),font));
            PdfPCell medi_content = new PdfPCell(new Phrase(final_text,font));    
            
            table.addCell(pet_name); // 테이블에 생성한 셀 데이터 추가
            table.addCell(medi_visit);
            table.addCell(medi_lname);
            table.addCell(medi_sname);
            table.addCell(medi_content);
            
            String pattern2 = "<img\\s+src=\"([^\"]+)\"";
            Pattern imgPattern = Pattern.compile(pattern2);
            Matcher matcher = imgPattern.matcher(unescapedContent);
            
            String fileName = "";
            while (matcher.find()) {
                String imgsrc = matcher.group(1);

                // 파일명 추출
                String[] parts = imgsrc.split("/");
                fileName = parts[parts.length - 1];
            }

            Image img = Image.getInstance("C:\\eclipse_project\\workspace_project\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\FinalProject_VET\\ckupload\\"+ fileName);
            
            img.scalePercent(50);
            
            document.add(img);
            
            document.add(table);
            
            document.close();
            
            result = "success";
 
        } catch (Exception e) {
            e.printStackTrace();
            result = "fail";
        }
        return result;
    }

}
