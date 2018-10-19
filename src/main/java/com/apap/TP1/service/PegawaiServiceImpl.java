package com.apap.TP1.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.apap.TP1.model.InstansiModel;
import com.apap.TP1.model.JabatanModel;
import com.apap.TP1.model.PegawaiModel;
import com.apap.TP1.repository.PegawaiDb;

@Service
@Transactional
public class PegawaiServiceImpl implements PegawaiService {
	
	@Autowired
	private PegawaiDb pegawaiDb;
	
	@Override
	public PegawaiModel getPegawaiByNip(String nip) {
		// TODO Auto-generated method stub
		
		return pegawaiDb.findByNip(nip);
	}

	
	
	@Override
	public double hitungGaji(String nip) {
		// TODO Auto-generated method stub
		PegawaiModel pegawai = pegawaiDb.findByNip(nip);
		List<JabatanModel> jabatan = pegawai.getJabatan();
		double gaji = 0;
		for(int i = 0 ; i < jabatan.size() ; i++) {
			double tunjangan = jabatan.get(i).getGaji_pokok() * (pegawai.getInstansi().getProvinsi().getPresentase_tunjangan()/100);
			gaji = jabatan.get(i).getGaji_pokok() + tunjangan;
		}
		return gaji;
	}

	@Override
	public void addPegawai(PegawaiModel pegawai) {
		// TODO Auto-generated method stub
		pegawaiDb.save(pegawai);
	}

	@Override
	public List<PegawaiModel> getAllPegawai() {
		// TODO Auto-generated method stub
		return pegawaiDb.findAll();
	}

}
