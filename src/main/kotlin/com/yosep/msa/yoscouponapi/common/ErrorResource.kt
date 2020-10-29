package com.yosep.msa.yoscouponapi.common

import org.springframework.hateoas.EntityModel
import org.springframework.hateoas.Link
import org.springframework.validation.Errors


class ErrorsResource(errors: Errors?, vararg links: Link?) : EntityModel<Errors?>(errors!!, *links) {
    init {
        add(Link(""))
    }
}