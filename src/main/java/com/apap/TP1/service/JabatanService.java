package com.apap.TP1.service;

import java.util.List;

import com.apap.TP1.model.JabatanModel;

public interface JabatanService {
	void addJabatan(JabatanModel jabatan);
	JabatanModel getJabatanById(long id);
	List<JabatanModel> getAllJabatan();
	void deleteJabatan(JabatanModel jabatan);
}
