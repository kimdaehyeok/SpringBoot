package com.example.demo.domain.pdsboard;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "tbl_pds")
public class PDSBoardCascading 
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long pid;
	
	private String pname;
	private String pwriter;

	/* JPA에서는 처리하려는 Entity 객체의 상태에 따라서 종속적인 객체들의 영속성도 같이 처리 되는 것을 '영속성 전이' 라고 한다.
	 * 
	 * ALL : 모든 변경에 대한 전이
	 * PERSIST : 저장 시에만 전이
	 * MERGE : 병합 시에만 전이
	 * REMOVE : 삭제 시에만 전이
	 * REFRESH : 엔티티 매니저의 refresh() 호출 시 전이
	 * DETACH : 부모 엔티티가 detach 되면 자식 엔티티 역시 detach
	 * 
	 * 영속성 전이는 데이터베이스의 트랜잭션과 유사해 보이지만, JPA에서는 메모리 상의 객체가 엔티티매니저의 영속 컨텍스트에 저장되는 경우
	 * 영속, 준영속, 비영속 등의 개념이 존재하기 때문에 더 복잡한 상태가 된다.
	 * JPA에서는 엔티티들이 기본적으로 메모리상의 관계다.
	 * */
	@OneToMany(cascade = CascadeType.ALL)
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
