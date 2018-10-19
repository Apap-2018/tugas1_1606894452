package com.apap.TP1.controller;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.apap.TP1.model.PegawaiModel;
import com.apap.TP1.service.InstansiService;
import com.apap.TP1.service.PegawaiService;

@Controller
public class PegawaiController {

	@Autowired
	private PegawaiService pegawaiService;
	@Autowired
	private InstansiService instansiService;

	
	//test with nip : 1101070770199401
	@RequestMapping("/pegawai/view")
	public String view(@RequestParam("nip") String nip, Model model) {
		PegawaiModel pegawai = pegawaiService.getPegawaiByNip(nip);
		double gaji = pegawaiService.hitungGaji(nip);
		model.addAttribute("pegawai", pegawai);
		model.addAttribute("gaji", gaji);
		return "view-pegawai";
	}
	
	@RequestMapping(value = "/pegawai/add", method = RequestMethod.GET)
	public String add(Model model) {
		model.addAttribute("pegawai", new PegawaiModel());
		return "addPegawai";
	}
	
	@RequestMapping(value = "/pegawai/add", method = RequestMethod.POST)
	private String addPegawaiSubmit(@ModelAttribute PegawaiModel pegawai) {
		pegawaiService.addPegawai(pegawai);
		return "berhasil-add-pegawai";
	}
	
	@RequestMapping(value="/pegawai/termuda-tertua/", method = RequestMethod.GET)
	private String lihatJabatan(@RequestParam("idInstansi") long idInstansi, Model model) {
		List<PegawaiModel> getPegawai = pegawaiService.getAllPegawai();
		List<PegawaiModel> newPegawai = new ArrayList<PegawaiModel>();
		for(int i = 0 ; i < getPegawai.size() ; i++) {
			if(getPegawai.get(i).getInstansi().getId() == idInstansi) {
				newPegawai.add(getPegawai.get(i));
			}
		}
		newPegawai.sort(new Comparator<PegawaiModel>() {
			public int compare(PegawaiModel p1, PegawaiModel p2) {
				return p1.getTanggal_lahir().compareTo(p2.getTanggal_lahir());
			}
		});		
		PegawaiModel tua = newPegawai.get(0);
		PegawaiModel muda = newPegawai.get(newPegawai.size()-1);
		model.addAttribute("tua", tua);
		model.addAttribute("muda", muda);
		return "lihat-pegawai-muda-tua";
	}
	
	
	
}
