package com.example.estdelivery.domain

import com.example.estdelivery.accountNumberArbitraryBuilder
import io.kotest.assertions.throwables.shouldNotThrow
import io.kotest.assertions.throwables.shouldThrowAny
import io.kotest.core.spec.style.FreeSpec
import net.jqwik.api.Arbitraries

class AccountNumberTest : FreeSpec({
    "계좌 번호를 생성 할 때" - {
        "10자리 임의 번호를 생성해" - {
            for (i in 0..300) {
                "$i 번째 계좌 번호를 생성한다." {
                    shouldNotThrow<IllegalArgumentException> {
                        accountNumberArbitraryBuilder().sample()
                    }
                }
            }
        }

        "경계값 - 계좌번호는 10자리 미만인" - {
            val arbitrarily = Arbitraries.strings().numeric().ofMinLength(0).ofMaxLength(9)

            for (i in 0..100) {
                val number = arbitrarily.sample()
                "${number}로 생성하면 예외가 발생한다." {
                    shouldThrowAny {
                        accountNumberArbitraryBuilder().set("accountNumber", number).sample()
                    }
                }
            }
        }

        "경계값 - 계좌번호가 10자리 초과인." - {
            val arbitrarily = Arbitraries.strings().numeric().ofMinLength(11).ofMaxLength(20)

            for (i in 0..100) {
                val number = arbitrarily.sample()
                "${number}로 생성하면 예외가 발생한다." {
                    shouldThrowAny {
                        accountNumberArbitraryBuilder().set("accountNumber", number).sample()
                    }
                }
            }
        }
    }
})
