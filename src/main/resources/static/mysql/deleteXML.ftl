    <delete id="del${className}" parameterType="int">
        delete from ${tableName} where
        <trim suffixOverrides="and">
            <#list attrs as attr>
                <#if attr.isKey == 1>
                    ${sense}${attr.columnName}${sense} = ${"#\{"}id}
                </#if>
            </#list>
        </trim>
    </delete>
