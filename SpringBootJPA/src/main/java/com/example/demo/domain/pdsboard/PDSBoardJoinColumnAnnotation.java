package com.example.demo.domain.pdsboard;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "tbl_pds")
public class PDSBoardJoinColumnAnnotation 
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long pid;
	
	private String pname;
	private String pwriter;
	
	/* PDSFile과 매핑되는 테이블에 pdsno 컬럼이 추가된 것을 확인 할 수 있다.
	 * */
	
	/* PDSBoard는 모든 PDSFile 객체들의 참조를 보관할 수 있으며, 원하는 모든 데이터에 대해서 처리가 가능하다.
	 * 그러나, PDSFile의 경우 PDSBoard에 대한 참조가 없기 때문에 단독으로 처리할 수 없다.
	 * 이처럼 불평등한 관계를 처리하기 위해 각각 Repository를 생성하는 대신에 
	 * 'One'에 해당하는 Entity 객체에 대한 Repository 만을 이용하는 것이 좋다.
	 * 참고로 DDD에서는 Aggregation(집합, 변경이 일어나는 단위가 여러 도메인 객체가 영향을 받는 관계)이라고 표현한다.
	 * */
	@OneToMany
	@JoinColumn(name = "pdsno")
	private List<PDSFile> files;

	public Long getPid() 
	{
		return pid;
	}

	public void setPid(Long pid) 
	{
		this.pid = pid;
	}

	public String getPname() 
	{
		return pname;
	}

	public void setPname(String pname) 
	{
		this.pname = pname;
	}

	public String getPwriter() 
	{
		return pwriter;
	}

	public void setPwriter(String pwriter) 
	{
		this.pwriter = pwriter;
	}

	public List<PDSFile> getFiles() 
	{
		return files;
	}

	public void setFiles(List<PDSFile> files) 
	{
		this.files = files;
	}
}
