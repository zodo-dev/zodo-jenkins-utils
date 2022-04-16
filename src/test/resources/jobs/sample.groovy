podTemplate([
    containers:
        [containerTemplate(
            args: '2d',
            command: 'sleep',
            name: 'busybox'
        )],
    inheritFrom: 'default',
    yamlMergeStrategy: 'merge'
])