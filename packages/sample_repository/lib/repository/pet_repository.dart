import 'fake_pet_repository.dart';

import '../models/pet.dart';

abstract class PetRepository {
  Future<List<Pet>> fetchPetList();
}

PetRepository get petRepository => FakePetRepository();
