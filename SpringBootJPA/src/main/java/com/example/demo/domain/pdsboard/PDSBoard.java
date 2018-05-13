package com.example.demo.domain.pdsboard;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "tbl_pds")
public class PDSBoard 
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long pid;
	
	private String pname;
	private String pwriter;
	
	/* tbl_pds에 컬럼이 생성된다면, 여러개 tbl_pdsfiles의 fno가 저장되어야 한다. 즉, 하나의 컬럼에 여러 값이 저장되어야 한다.
	 * 그러나 데이터베이스 컬럼은 기본적으로 하나의 값을 갖는것이 정상이다.
	 * 그러므로 @OneToMany로 지정되면 무조건 여러개의 데이터를 저장하기 위해서 별도의 테이블을 생성한다.
	 * 
	 * 별도의 테이블 생성이 싫다면, 특정한 테이블을 조인할 것이라고 명시(@JoinTable)하거나
	 * @JoinColumn을 명시해 기존 테이블을 이용해서 조인한다고 표현해야 한다. 
	 * 
	 * @JoinTable은 자동으로 생성되는 테이블 대신에 별도의 이름을 가진 테이블을 생성하고자 할 때 사용한다.
	 * @JoinColumn은 이미 존재하는 테이블에 컬럼을 추가할 때 사용한다.
	 * */
	@OneToMany
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
