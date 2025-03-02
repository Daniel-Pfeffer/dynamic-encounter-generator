package at.jku.deq.api.dto

import at.jku.deq.shared.AbstractFactory

class StatDtoFactory(
    postProcessor: (StatDto) -> StatDto = { it }
) : AbstractFactory<StatDto>(postProcessor) {

    fun create(
        strength: Long = 10,
        dexterity: Long = 10,
        constitution: Long = 10,
        intelligence: Long = 10,
        wisdom: Long = 10,
        charisma: Long = 10
    ): StatDto {
        return createInternal(
            {}, StatDto(
                strength = strength,
                dexterity = dexterity,
                constitution = constitution,
                intelligence = intelligence,
                wisdom = wisdom,
                charisma = charisma
            )
        )
    }
}