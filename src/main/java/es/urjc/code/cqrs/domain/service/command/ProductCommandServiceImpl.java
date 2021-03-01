package es.urjc.code.cqrs.domain.service.command;

import java.util.UUID;

import org.modelmapper.ModelMapper;

import es.urjc.code.cqrs.domain.dto.FullProductDTO;
import es.urjc.code.cqrs.domain.dto.ProductDTO;
import es.urjc.code.cqrs.domain.repository.ProductRepository;

public class ProductCommandServiceImpl implements ProductCommandService {

	private ProductRepository repository;
	ModelMapper mapper = new ModelMapper();
	
	public ProductCommandServiceImpl(ProductRepository repository) {
		this.repository = repository;
	}
	
	
	@Override
	public FullProductDTO createProduct(ProductDTO productDTO) {
		FullProductDTO fullProductDTO = mapper.map(productDTO, FullProductDTO.class);
		fullProductDTO.setId(UUID.randomUUID());
		FullProductDTO saveFullProductDTO = repository.save(fullProductDTO);

		return (saveFullProductDTO != null) ? saveFullProductDTO : fullProductDTO;
	}

	@Override
	public FullProductDTO deleteProduct(UUID id) {
		FullProductDTO product = repository.findById(id);
		repository.deleteById(id);

		return product;
	}
}