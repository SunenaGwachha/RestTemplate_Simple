# RestTemplate_Simple

From MicroserviceA

  http://localhost:1081/getA/
  
  http://localhost:1081/callB/

From MicroserviceB

  http://localhost:1082/callA/
  
  http://localhost:1082/getB/


Note for Microservices using Cloud we use @LoadBalanced if we have multiple instance of microservice(in case of openfeign we don need this annotation it will abalance load automatically)

RestTemplate.exchange use garayko sample from suresh EA final project

	private BookingService bookingService;

	@PostMapping("/addbooking")
	public String add(@RequestBody Bookings booking, HttpServletRequest request) {
		

		HttpHeaders headers = new HttpHeaders();
		headers.set("Authorization", request.getHeader("Authorization"));
		HttpEntity<String> entity = new HttpEntity<String>(headers);
		ResponseEntity<String> response = restTemplate.exchange("http://localhost:8055/getusername", HttpMethod.GET,
				entity, String.class);
		
		booking.setUser_id(response.getBody());	
		
		//String total="100";
		

		long totalDays=ChronoUnit.DAYS.between(booking.getStart_date(), booking.getEnd_date());
		double total=totalDays*booking.getUnit_price();
		
		PaymentDTO paymentDTO=new PaymentDTO(booking.getUser_id(),Double.toString(total));
		
		//System.out.println(Double.toString(total));
		 HttpEntity<PaymentDTO> requestEntity = new HttpEntity<>(paymentDTO, headers);		

		
		ResponseEntity<String> paymentResponse = restTemplate.exchange("http://34.70.158.172/makePayment/paypal", HttpMethod.POST,
				requestEntity, String.class);	
		
		
		bookingService.book(booking);
		
		this.producer.sendMessage(booking.getUser_id(),paymentResponse.getBody());	
	
		return paymentResponse.getBody();
		
	}
