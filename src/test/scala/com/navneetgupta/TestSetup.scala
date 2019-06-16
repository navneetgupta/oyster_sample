package com.navneetgupta

import cats.Monad
import cats.implicits._
import com.navneetgupta.domain.{CardServices, CardsRepository, ZoneServices, ZonesRepository}
import com.navneetgupta.infra.{InMemoryCardsRepositoryInterpreter, InMemoryZonesRepositoryInterpreter}

trait TestSetup {
  class TestSetup[F[_]: Monad]() {
    val cardRepo : CardsRepository[F] = InMemoryCardsRepositoryInterpreter[F]
    val zonesRepo: ZonesRepository[F] = InMemoryZonesRepositoryInterpreter[F]

    val zoneServices: ZoneServices[F] = ZoneServices[F](zonesRepo)
    val cardServices: CardServices[F] = CardServices[F](cardRepo, zoneServices)
  }
}
