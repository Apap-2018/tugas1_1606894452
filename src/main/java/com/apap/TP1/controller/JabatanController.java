package com.apap.TP1.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.apap.TP1.model.JabatanModel;
import com.apap.TP1.service.JabatanService;

@Controller
public class JabatanController {
	
	@Autowired
	private JabatanService jabatanService;
	
	@RequestMapping(value = "/jabatan/add", method=RequestMethod.GET)
	public String add(Model model) {
		model.addAttribute("jabatan", new JabatanModel());
		return "add-jabatan";
	}
	
	@RequestMapping(value="/jabatan/add", method=RequestMethod.POST)
	public String addJabatanSubmit(@ModelAttribute JabatanModel jabatan){
		jabatanService.addJabatan(jabatan);
		return "berhasil-add-jabatan";
	}
	
	@RequestMapping(value="/jabatan/update/{Id}", method=RequestMethod.GET)
	private String updateJabatanById(@PathVariable(value="Id") long Id, Model model) {
		JabatanModel jabatan = jabatanService.getJabatanById(Id);
		model.addAttribute("jabatan", jabatan);
		return "update-Jabatan";
	}
	
	@RequestMapping(value="jabatan/update",method = RequestMethod.POST)
	private String updateJabatanSubmit(@ModelAttribute JabatanModel jabatan)	{
		jabatanService.addJabatan(jabatan);
		return "berhasil-update-jabatan";
	}
	
	@RequestMapping("/jabatan/view/")
	private String lihatJabatan(@RequestParam("idJabatan") long Id, Model model) {
		JabatanModel jabatan = jabatanService.getJabatanById(Id);
		model.addAttribute("jabatan", jabatan);
		return "lihat-jabatan";
	}
	
	@RequestMapping("/jabatan/viewall")
	private String lihatSemuaJabatan(Model model) {
		List<JabatanModel> getJabatan = jabatanService.getAllJabatan();
		model.addAttribute("Listjabatan", getJabatan);
		return "lihat-semua-jabatan";
		
	}
	
	@RequestMapping(value = "/jabatan/delete/{Id}")
	private String deleteJabatan(@PathVariable(value="Id") long Id) {
		try {
		JabatanModel hapusJabatan = jabatanService.getJabatanById(Id);
		jabatanService.deleteJabatan(hapusJabatan);
			return "berhasil-hapus-jabatan";
		}
		catch(Exception ex){
			return "gagal-hapus-jabatan";
		}
		
	}
}
