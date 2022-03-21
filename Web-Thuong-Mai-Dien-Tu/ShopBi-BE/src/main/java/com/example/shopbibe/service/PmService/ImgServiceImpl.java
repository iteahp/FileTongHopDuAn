package com.example.shopbibe.service.PmService;

import com.example.shopbibe.model.Img;
import com.example.shopbibe.repository.PmRepo.IImgRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ImgServiceImpl implements IImgService {
    @Autowired
    IImgRepository imgRepository;


    @Override
    public List<Img> getImgByProductId(Long id) {
        return imgRepository.getImgByProductId(id);
    }

    @Override
    public void delete(long id) {
        imgRepository.deleteById(id);
    }

    @Override
    public Img save(Img img) {
        return imgRepository.save(img);
    }

    @Override
    public Img findById(long id) {
        return imgRepository.findById(id).get();
    }
}
