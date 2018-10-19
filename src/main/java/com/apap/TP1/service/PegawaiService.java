package com.apap.TP1.service;

import java.util.List;

import com.apap.TP1.model.InstansiModel;
import com.apap.TP1.model.PegawaiModel;

public interface PegawaiService {
	PegawaiModel getPegawaiByNip(String nip);
	double hitungGaji(String nip);
	void addPegawai(PegawaiModel pegawai);
	List<PegawaiModel> getAllPegawai();
}
