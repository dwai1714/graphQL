package com.dc.GraphQL;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.dc.GraphQL.exception.GraphQLErrorAdapter;
import com.dc.GraphQL.model.Author;
import com.dc.GraphQL.model.Book;
import com.dc.GraphQL.model.UserRating;
import com.dc.GraphQL.repository.AuthorRepository;
import com.dc.GraphQL.repository.BookRepository;
import com.dc.GraphQL.repository.UserRatingRepository;
import com.dc.GraphQL.resolver.BookResolver;
import com.dc.GraphQL.resolver.Mutation;
import com.dc.GraphQL.resolver.Query;
import com.dc.GraphQL.service.MovieService;

import graphql.ExceptionWhileDataFetching;
import graphql.GraphQLError;
import graphql.servlet.GraphQLErrorHandler;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
//		List<String> l1 = Arrays.asList("a", "b", "c");
//		List<String> l2 = Arrays.asList("d", "f");
//		List<String> l3 = Arrays.asList("g", "h", "i", "j");
//		List<String> l4 = Arrays.asList("aa", "bb", "cc", "dd");
//
//		List<List<String>> lists = new ArrayList<List<String>>();
//		lists.add(l1);
//		lists.add(l2);
//		lists.add(l3);
//		lists.add(l4);
//
//		Set<List<String>> combs = getCombinations(lists);
//		for (List<String> list : combs) {
//			System.out.println(list.toString());
//		}
		SpringApplication.run(Application.class, args);
	}

	@Bean
	public GraphQLErrorHandler errorHandler() {
		return new GraphQLErrorHandler() {
			@Override
			public List<GraphQLError> processErrors(List<GraphQLError> errors) {
				List<GraphQLError> clientErrors = errors.stream().filter(this::isClientError)
						.collect(Collectors.toList());

				List<GraphQLError> serverErrors = errors.stream().filter(e -> !isClientError(e))
						.map(GraphQLErrorAdapter::new).collect(Collectors.toList());

				List<GraphQLError> e = new ArrayList<>();
				e.addAll(clientErrors);
				e.addAll(serverErrors);
				return e;
			}

			protected boolean isClientError(GraphQLError error) {
				return !(error instanceof ExceptionWhileDataFetching || error instanceof Throwable);
			}
		};
	}

	@Bean
	public BookResolver authorResolver(AuthorRepository authorRepository) {
		return new BookResolver(authorRepository);
	}

	@Bean
	public Query query(AuthorRepository authorRepository, BookRepository bookRepository, MovieService movieService) {
		return new Query(authorRepository, bookRepository, movieService);
	}

	@Bean
	public Mutation mutation(AuthorRepository authorRepository, BookRepository bookRepository) {
		return new Mutation(authorRepository, bookRepository);
	}

	@Bean
	public CommandLineRunner demo(AuthorRepository authorRepository, BookRepository bookRepository,
			UserRatingRepository userRatingRepository) {
		return (args) -> {
			Author author = new Author("Herbert", "Schildt");
			Author author1 = new Author("Python", "Henry");
			authorRepository.save(author);
			authorRepository.save(author1);
			UserRating rating = new UserRating("Home", 5);
			userRatingRepository.save(rating);
			UserRating rating1 = new UserRating("Home Alone", 8);
			userRatingRepository.save(rating1);
			UserRating rating2 = new UserRating("Sholay", 8);
			userRatingRepository.save(rating2);

			bookRepository.save(new Book("Java: A Beginner's Guide, Sixth Edition", "0071809252", 728, author));
			bookRepository.save(new Book("Python: A Beginner's Guide, Sixth Edition", "0071809252", 200, author1));
		};
	}

//	public static <T> Set<List<T>> getCombinations(List<List<T>> lists) {
//		Set<List<T>> combinations = new HashSet<List<T>>();
//		Set<List<T>> newCombinations;
//
//		int index = 0;
//
//		// extract each of the integers in the first list
//		// and add each to ints as a new list
//		for (T i : lists.get(0)) {
//			List<T> newList = new ArrayList<T>();
//			newList.add(i);
//			combinations.add(newList);
//		}
//		index++;
//		while (index < lists.size()) {
//			List<T> nextList = lists.get(index);
//			newCombinations = new HashSet<List<T>>();
//			for (List<T> first : combinations) {
//				for (T second : nextList) {
//					List<T> newList = new ArrayList<T>();
//					newList.addAll(first);
//					newList.add(second);
//					newCombinations.add(newList);
//				}
//			}
//			combinations = newCombinations;
//
//			index++;
//		}
//
//		return combinations;
//	}
//
//	private String[] getColumnAsArray(String header) throws Exception{
//	        String excelFilePath = "book3.xlsx";
//	        FileInputStream inputStream = new FileInputStream(new File(excelFilePath));
//
//	        Workbook workbook = new XSSFWorkbook(inputStream);
//	        Sheet sheet = workbook.getSheetAt(0);
//
//		List<String> values = new ArrayList<>();
//
//		int columnOfIntrest = -1;
//
//		// find the interresting column
//		for (Cell cell : sheet.getRow(sheet.getFirstRowNum())) {
//			if (cell.getCellType() == Cell.CELL_TYPE_STRING && cell.getStringCellValue().equals("Mitesh")) { // this is
//																												// working
//				columnOfIntrest = cell.getColumnIndex();
//				break;
//			}
//		}
//		// nothing found
//		if (columnOfIntrest < 0)
//			return null;
//		Iterator<Row> rowIter = sheet.iterator();
//
//		if (rowIter.hasNext())
//			rowIter.next(); // skip the first row
//
//		while (rowIter.hasNext()) {
//			Cell currCell = rowIter.next().getCell(columnOfIntrest);
//
//			switch (currCell.getCellType()) {
//			case Cell.CELL_TYPE_STRING:
//				values.add(currCell.getStringCellValue());
//				break;
//			case Cell.CELL_TYPE_BOOLEAN:
//				values.add(Boolean.toString(currCell.getBooleanCellValue()));
//				break;
//			case Cell.CELL_TYPE_NUMERIC:
//				values.add(Double.toString(currCell.getNumericCellValue()));
//				break;
//			}
//		}
//
//		return values.toArray(new String[values.size()]);
//	}
}
