package com.yosep.msa.yoscouponapi.common

import com.fasterxml.jackson.databind.ObjectMapper
import org.junit.Ignore
import org.junit.runner.RunWith
import org.modelmapper.ModelMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.annotation.Import
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.test.web.servlet.MockMvc
import javax.transaction.Transactional


@RunWith(SpringRunner::class)
@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureRestDocs
@Transactional
@Import(RestDocsConfiguration::class) // 다른 스프링 빈 설정파일을 읽어와서 사용하는 방법 중 하나.
@ActiveProfiles("dev") // test환경에서 확인하겠다는 의미.
@Ignore // 테스트를 실행하지 않겠다는 의미
class BaseControllerTest {
    @Autowired
    protected lateinit var mockMvc: MockMvc

    @Autowired
    protected lateinit var objectMapper: ObjectMapper

    @Autowired
    protected lateinit var modelMapper: ModelMapper
}