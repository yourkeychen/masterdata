package com.thunisoft.service;

import com.thunisoft.pojo.SysRegistry;

import java.util.Map;

public interface XtzcService {
    Map insertObject(SysRegistry sysRegistry);
    Map updateByPrimaryKey(SysRegistry sysRegistry);
    Map deleteObject(SysRegistry sysRegistry);
}
