[![Figma Design System](https://img.shields.io/badge/Figma-Design-pink.svg?style=for-the-badge&logo=figma)](https://www.figma.com/file/iJbFMd9ZzU2U9iQZ0nk72t/Fractal-Foundations)

# About
A place for ✨👨‍🔬 personal exploration 👩‍🔬✨ of best practices for Compose, design systems, testing, modularization, linting & enforcement of best practices, Gradle config, build speeds, CI, etc.

I experiment here before subjecting teammates to the bleeding edge.

This is a monorepo containing multiple apps, mainly to allow me to amortize the cost of project setup over multiple projects, in a way that I can easily maintain (Gradle, design systems, etc.)

## Sample apps
- **Compose Movie Search** - (Module: `:apps:compose-movie-search:app`) A simple single-screen app that allows you to search / filter a list of movies. A small sample showing Unidirectional Data Flow (UDF) via MVI, including tests.
- **Compose Commerce** - (Module: `:apps:compose-commerce:app`) - An online shopping app that integrates with the [BigCommerce](https://www.bigcommerce.com) API.

## Modules
This is a Gradle monorepo, with many modules:
![Modules Graph](/utils/art/project.dot.png)

## License
```
Copyright (C) 2022 Pete Doyle

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```
