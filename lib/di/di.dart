import 'package:flutter_bloc/flutter_bloc.dart';
import 'package:sample_repository/sample_repository.dart';

import '../feed/feed.dart';

List<BlocProvider> blocProviders = [
  BlocProvider<PetFeedBloc>(
    create: (context) => PetFeedBloc(
      petRepository: context.read<PetRepository>(),
    ),
  ),
];

List<RepositoryProvider> repositoryProviers = [
  RepositoryProvider<PetRepository>(create: (context) => petRepository),
];
