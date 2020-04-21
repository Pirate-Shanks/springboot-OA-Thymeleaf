package com.github.myoa.service;


import com.github.myoa.domain.Emp;
import com.github.myoa.domain.EmpExample;
import com.github.myoa.dto.EmpDto;
import com.github.myoa.mapper.EmpMapper;
import com.github.myoa.util.CopyUtil;
import com.github.myoa.util.UuidUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Transactional
@Service
public class EmpService {


    @Resource
    EmpMapper empMapper;

    public List<EmpDto> findAll(){
        EmpExample empExample = new EmpExample();
        List<Emp> emps = empMapper.selectByExample(empExample);
        List<EmpDto> list= CopyUtil.copyList(emps,EmpDto.class);
        return list;
    }

    public void save(EmpDto empDto) {
        Emp emp = CopyUtil.copy(empDto, Emp.class);
        emp.setId(UuidUtil.getShortUuid());
        empMapper.insert(emp);
    }

    public void delete(String id) {
        empMapper.deleteByPrimaryKey(id);
    }

    public void update(EmpDto empDto) {
        Emp emp = CopyUtil.copy(empDto, Emp.class);
        empMapper.updateByPrimaryKey(emp);
    }

    public EmpDto find(String id) {
         return CopyUtil.copy(empMapper.selectByPrimaryKey(id),EmpDto.class);
    }
}
