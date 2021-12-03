package com.example.fitshop.service.impl;

import com.example.fitshop.enums.ProductCategoryEnum;
import com.example.fitshop.model.entity.ProductEntity;
import com.example.fitshop.model.service.ProductAddServiceModel;
import com.example.fitshop.model.service.ProductUpdateServiceModel;
import com.example.fitshop.model.view.ProductDetailsViewModel;
import com.example.fitshop.model.view.ProductViewModel;
import com.example.fitshop.repository.ProductRepository;
import com.example.fitshop.service.CloudinaryService;
import com.example.fitshop.service.ProductService;
import com.example.fitshop.web.exception.ObjectNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ModelMapper modelMapper;
    private final CloudinaryService cloudinaryService;

    public ProductServiceImpl(ProductRepository productRepository, ModelMapper modelMapper, CloudinaryService cloudinaryService) {
        this.productRepository = productRepository;
        this.modelMapper = modelMapper;
        this.cloudinaryService = cloudinaryService;
    }

    @Override
    public void initProducts() {
        if (this.productRepository.count() == 0) {
            ProductEntity bench = new ProductEntity();
            bench
                    .setBrandName("NordicTrack")
                    .setCategory(ProductCategoryEnum.WEIGHTS)
                    .setName("Adjustable bench")
                    .setPrice(BigDecimal.valueOf(500))
                    .setDescription("The NordicTrack Adjustable Weight Bench" +
                            " allows you to target a variety of muscles in your" +
                            " upper and lower body for a comprehensive and challenging" +
                            " workout in the privacy of your home. Easily adjust your weight" +
                            " bench between flat, incline, or military positions. Develop and tone" +
                            " your shoulders, upper chest, and triceps. Training in a decline will" +
                            " target your lower chest, lats, triceps, and front delts, allowing you to" +
                            " develop muscle evenly across your upper body. A high-density foam-padded backrest" +
                            " provides a comfortable lifting experience for sustained exercise. This helps you get" +
                            " the most out of your equipment. Your NordicTrack Adjustable Weight Bench features carefully" +
                            " box-stitched and sewn seats. These are more durable than stapled-on seat covers and add a" +
                            " professional touch to any home gym. Enjoy the added benefit of a professionally-designed" +
                            " exercise chart, developed by a certified personal trainer, to guide you through a variety of" +
                            " effective exercises and show proper form.")
                    .setImageUrl("https://res.cloudinary.com/algruev/image/upload/v1636545225/Fitshop/bench_nmkffu.jpg");

            ProductEntity bike = new ProductEntity();
            bike
                    .setBrandName("Digme")
                    .setCategory(ProductCategoryEnum.CARDIO)
                    .setName("Cardio bike")
                    .setPrice(BigDecimal.valueOf(4000))
                    .setDescription("The bike involved here is the Keiser m3i Lite," +
                            "which is a terrific bit of kit with a heavy flywheel that provides" +
                            " a smooth and near-silent ride, and the resistance can be easily jacked" +
                            " up with the lever on the handlebars. The console is a little basic but the" +
                            " bike will link up to London studio Digme’s At Home app, where you’ll find a" +
                            " wealth of live and on-demand spinning classes to join.\n" +
                            " Digme’s service feels a little different to those from the likes of" +
                            " Peloton and Echelon. Each live class is a Zoom session and the number " +
                            " of people in the class is much smaller. It makes for a more communal feel," +
                            " especially given the instructors can chat with the class. There are daily live" +
                            " classes in the morning, at lunchtime and in the evening, and there’s a sizeable" +
                            " library of on-demand sessions if those times don’t suit you.")
                    .setImageUrl("https://res.cloudinary.com/algruev/image/upload/v1636545225/Fitshop/bike_tscsyf.jpg");

            ProductEntity bands = new ProductEntity();
            bands
                    .setBrandName("Bodylastics")
                    .setCategory(ProductCategoryEnum.BAND)
                    .setName("Resistance Bands")
                    .setPrice(BigDecimal.valueOf(200))
                    .setDescription("MAX TENSION Set (44kg.) with 5 anti-snap exercise tubes," +
                            " Heavy Duty components, carrying case, DVD and FREE 3 month membership to LIVEEXERCISE website ")
                    .setImageUrl("https://res.cloudinary.com/algruev/image/upload/v1636545226/Fitshop/bands_ne0t54.jpg");

            ProductEntity dumbbells = new ProductEntity();
            dumbbells
                    .setBrandName("Rogue")
                    .setCategory(ProductCategoryEnum.WEIGHTS)
                    .setName("Dumbbells(2 x 22.5 kg)")
                    .setPrice(BigDecimal.valueOf(400))
                    .setDescription("Hex Dumbbell Hand Weights\n" +
                            " Solid cast dumbbells with black rubber coating.\n" +
                            " Durable coating will not crack or fade, and prevents damage to floors and equipment.\n" +
                            " Supplied with a coating of preservative to preserve the rubber during storage and transport." +
                            " Please remove this with a cloth prior to use and do not place on carpets or floors until clean.\n" +
                            " Excellent contoured steel grips for maximum comfort during extended workouts ")
                    .setImageUrl("https://res.cloudinary.com/algruev/image/upload/v1636545225/Fitshop/dumbbells_mjsqs0.jpg");

            this.productRepository.saveAll(List.of(bench, bike, bands, dumbbells));
        }
    }

    @Override
    @Cacheable("cheapestProducts")
    public List<ProductViewModel> getTheFourCheapest() {
        return this.productRepository
                .findTheCheapest()
                .stream()
                .limit(4)
                .map(p -> this.modelMapper.map(p, ProductViewModel.class))
                .collect(Collectors.toList());
    }

    @Override
    public ProductDetailsViewModel getViewModelById(Long id) {
        return this.productRepository
                .findById(id)
                .map(p -> this.modelMapper.map(p, ProductDetailsViewModel.class))
                .orElseThrow(() -> new ObjectNotFoundException("Product with id " + id + " does not exist!"));
    }

    @Override
    public ProductEntity getById(Long id) {
        return this.productRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException("Product with id " + id + " does not exist!"));
    }

    @Override
    public void add(ProductAddServiceModel productAddServiceModel) throws IOException {
        MultipartFile picture = productAddServiceModel.getPicture();
        String pictureUrl = this.cloudinaryService.uploadPicture(picture);
        ProductEntity productEntity = this.modelMapper.map(productAddServiceModel, ProductEntity.class);
        productEntity.setImageUrl(pictureUrl);

        this.productRepository.save(productEntity);
    }

    @Override
    public List<ProductViewModel> getAll() {
        return this.productRepository
                .findAll()
                .stream()
                .map(p -> this.modelMapper.map(p, ProductViewModel.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<ProductViewModel> getTheNewestEight() {
        return this.productRepository
                .findTheNewest()
                .stream().map(p -> this.modelMapper.map(p, ProductViewModel.class))
                .limit(8)
                .collect(Collectors.toList());
    }

    @Override
    public List<ProductViewModel> getByCategory(ProductCategoryEnum category) {
        return this.productRepository
                .findByCategory(category)
                .stream()
                .map(p -> this.modelMapper.map(p, ProductViewModel.class))
                .collect(Collectors.toList());
    }

    @Override
    public void deleteById(Long id) {
        this.productRepository.deleteById(id);
    }

    @Override
    public ProductEntity getByName(String name) {
        return this.productRepository.getByName(name);
    }

    @Override
    public void update(ProductUpdateServiceModel productUpdateServiceModel) {
        ProductEntity productEntity = this.productRepository
                .findById(productUpdateServiceModel.getId())
                .orElseThrow(() -> new ObjectNotFoundException("Product with id " + productUpdateServiceModel.getId() + " does not exist!"));

        productEntity
                .setDescription(productUpdateServiceModel.getDescription())
                .setPrice(productUpdateServiceModel.getPrice())
                .setName(productUpdateServiceModel.getName());

        this.productRepository.save(productEntity);
    }
}
